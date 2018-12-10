package uk.ashleybye.httpserver.http;

public interface Request {

  String getMethod();

  String getUri();

  String getProtocolVersion();

  String getBody();
}
