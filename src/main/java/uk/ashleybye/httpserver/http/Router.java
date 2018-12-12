package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.http.router.Controller;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public interface Router {

  Router addRoute(String uri, Controller controller);

  Response route(Request request);
}
