package uk.ashleybye.httpserver.http;

import java.util.ArrayList;
import java.util.List;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;
import uk.ashleybye.httpserver.server.Router;

public class HttpRouter implements Router {

  private List<String> routes = new ArrayList<>();

  @Override
  public Router addRoute(String route) {
    routes.add(route);
    return this;
  }

  @Override
  public boolean hasRoute(String uri) {
    return routes.contains(uri);
  }

  @Override
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
    response.setStatusCode(StatusCode.OK);
    response.setBody("");
  }

  private void handleNotFound(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.NOT_FOUND);
    response.setBody("");
  }
}
