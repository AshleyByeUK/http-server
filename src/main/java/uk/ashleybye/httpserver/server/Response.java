package uk.ashleybye.httpserver.server;

public interface Response {

  String getProtocolVersion();

  int getStatusCode();

  String getStatusMessage();

  String getBody();
}
