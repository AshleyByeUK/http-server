package uk.ashleybye.httpserver.server;

public interface Middleware {

  String processMessage(String message);
}
