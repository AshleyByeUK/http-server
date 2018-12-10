package uk.ashleybye.httpserver.http;

public interface Response {

  String getProtocolVersion();

  int getStatusCode();

  String getStatusMessage();

  String getBody();
}
