package uk.ashleybye.httpserver.server;

public interface RequestHandler {

  String handle(String message);
}
