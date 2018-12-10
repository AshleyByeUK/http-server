package uk.ashleybye.httpserver.server;

public interface RequestHandler {

  Response handle(Request request);
}
