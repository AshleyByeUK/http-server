package uk.ashleybye.httpserver.http.router;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.httpserver.http.controller.HttpController;
import uk.ashleybye.httpserver.server.Controller;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;
import uk.ashleybye.httpserver.server.Router;

public class HttpRouter implements Router {

  private Map<String, Controller> controllers = new HashMap<>();

  public HttpRouter addRoute(String uri, Controller controller) {
    controllers.putIfAbsent(uri, controller);
    return this;
  }

  @Override
  public Response route(Request request) {
    Controller controller = controllers.getOrDefault(request.getUri(), HttpController.routeNotFoundController());
    return request.respond(controller);
  }
}
