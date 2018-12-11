package uk.ashleybye.httpserver.http;

import java.util.ArrayList;
import java.util.List;

public class Router {

  private List<String> routes = new ArrayList<>();

  public Router addRoute(String route) {
    routes.add(route);
    return this;
  }

  public boolean hasRoute(String uri) {
    return routes.contains(uri);
  }

  public Response route(Request request) {
    Response response = new HttpResponse();
    if (hasRoute(request.getUri())) {
      handleRequest(request, response);
    } else {
      handleNotFound(request, response);
    }
    return response;
  }

  private void handleRequest(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(200);
    response.setStatusMessage("OK");
    response.setBody("");
  }

  private void handleNotFound(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(404);
    response.setStatusMessage("Not Found");
    response.setBody("");
  }
}
