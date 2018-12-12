package uk.ashleybye.httpserver.http.router;

import java.util.List;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.Route;
import uk.ashleybye.httpserver.http.StatusCode;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class HttpRoute implements Route {

  private final Controller controller;
  private final List<RequestMethod> allowedMethods;

  public HttpRoute(Controller controller, List<RequestMethod> allowedMethods) {
    this.controller = controller;
    this.allowedMethods = allowedMethods;
  }

  public static Route notFoundRoute() {
    return (request, response) -> {
      response.setProtocolVersion(request.getProtocolVersion());
      response.setStatusCode(StatusCode.NOT_FOUND);
      response.setBody("");
      return response;
    };
  }

  @Override
  public Response handleRequest(Request request, Response response) {
    if (request.getMethod().equals(RequestMethod.HEAD)) {
      controller.head(request, response);
    } else if (request.getMethod().equals(RequestMethod.GET) && allowedMethods.contains(RequestMethod.GET)) {
      controller.get(request, response);
    }
    return response;
  }
}
