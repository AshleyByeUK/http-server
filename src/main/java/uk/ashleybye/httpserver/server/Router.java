package uk.ashleybye.httpserver.server;

public interface Router {

  Router addRoute(String route);

  boolean hasRoute(String uri);

  Response route(Request request);
}
