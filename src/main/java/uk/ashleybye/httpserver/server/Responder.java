package uk.ashleybye.httpserver.server;

@FunctionalInterface
public interface Responder {

  void respond(Request req, Response resp);
}
