package uk.ashleybye.httpserver.http;

public interface Response {

  String getProtocolVersion();

  void setProtocolVersion(String protocolVersion);

  int getStatusCode();

  void setStatusCode(int statusCode);

  String getStatusMessage();

  void setStatusMessage(String statusMessage);

  String getBody();

  void setBody(String body);
}
