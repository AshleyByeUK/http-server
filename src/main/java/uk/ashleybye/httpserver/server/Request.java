package uk.ashleybye.httpserver.server;

public interface Request {

  String getMethod();

  String getUri();

  String getProtocolVersion();

  String getBody();
}
