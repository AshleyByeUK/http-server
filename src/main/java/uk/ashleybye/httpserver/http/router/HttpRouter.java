package uk.ashleybye.httpserver.http.router;

import static uk.ashleybye.httpserver.http.ProtocolVersion.HTTP_1_1;
import static uk.ashleybye.httpserver.http.StatusCode.NOT_FOUND;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.httpserver.http.response.HttpResponse;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;
import uk.ashleybye.httpserver.server.Router;

public class HttpRouter implements Router {

  private Map<String, HttpRoute> routes = new HashMap<>();

  public HttpRoute addRoute(String uri) {
    routes.putIfAbsent(uri, new HttpRoute(this));
    return routes.get(uri);
  }

  public Response route(Request request) {
    if (validRoute(request)) {
      return routedResponse(request);
    } else if (request.hasParseError()) {
      return parsingErrorResponse(request);
    } else {
      return notFoundResponse();
    }
  }

  private boolean validRoute(Request request) {
    return routes.containsKey(request.getUri());
  }

  private Response routedResponse(Request request) {
    return routes.get(request.getUri()).handle(request);
  }

  private Response parsingErrorResponse(Request request) {
    Response response = new HttpResponse();
    request.respond(null, response);
    return response;
  }

  private Response notFoundResponse() {
    Response response = new HttpResponse();
    response.setProtocolVersion(HTTP_1_1);
    response.setStatusCode(NOT_FOUND);
    return response;
  }
}
