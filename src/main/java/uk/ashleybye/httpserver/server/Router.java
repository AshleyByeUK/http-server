package uk.ashleybye.httpserver.server;

public interface Router {

  Response route(Request request);
}
