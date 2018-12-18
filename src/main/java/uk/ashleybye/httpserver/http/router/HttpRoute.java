package uk.ashleybye.httpserver.http.router;

import static uk.ashleybye.httpserver.http.RequestMethod.HEAD;
import static uk.ashleybye.httpserver.http.RequestMethod.OPTIONS;
import static uk.ashleybye.httpserver.http.StatusCode.METHOD_NOT_ALLOWED;
import static uk.ashleybye.httpserver.http.StatusCode.OK;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.StatusCode;
import uk.ashleybye.httpserver.http.response.HttpResponse;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Responder;
import uk.ashleybye.httpserver.server.Response;

public class HttpRoute {

  private final HttpRouter router;
  private Map<RequestMethod, Responder> handlers = new HashMap<>();

  HttpRoute(HttpRouter router) {
    this.router = router;
    handlers.put(OPTIONS, OptionsResponder.getResponder(handlers.keySet()));
    handlers.put(HEAD, HeadResponder.getResponder());
  }

  public HttpRouter useCustomResponder(RequestMethod method, Responder responder) {
    handlers.put(method, responder);
    return router;
  }

  public HttpRouter useDefaultResponder() {
    return router;
  }

  public HttpRouter useRedirectResponder(RequestMethod method, String uri) {
    handlers.put(method, RedirectResponder.getResponder(uri));
    return router;
  }

  Response handle(Request request) {
    if (handlers.containsKey(request.getMethod())) {
      return makeResponse(request, OK, handlers.get(request.getMethod()));
    } else {
      return makeResponse(request, METHOD_NOT_ALLOWED, handlers.get(OPTIONS));
    }
  }

  private Response makeResponse(Request request, StatusCode statusCode, Responder responder) {
    HttpResponse response = new HttpResponse();
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(statusCode);
    request.respond(responder, response);
    return response;
  }
}
