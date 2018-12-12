package uk.ashleybye.httpserver.http.router;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.httpserver.http.HttpResponse;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.Route;
import uk.ashleybye.httpserver.http.Router;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class HttpRouter implements Router {

  private Map<String, Route> routes = new HashMap<>();

  @Override
  public HttpRouter addRoute(String uri, Controller controller, RequestMethod... allowedMethods) {
    HttpRoute route = new HttpRoute(controller, Arrays.asList(allowedMethods));
    routes.putIfAbsent(uri, route);
    return this;
  }

  @Override
  public Response route(Request request) {
    Response response = new HttpResponse();
    Route route = routes.getOrDefault(request.getUri(), HttpRoute.notFoundRoute());
    return route.handleRequest(request, response);
  }
}
