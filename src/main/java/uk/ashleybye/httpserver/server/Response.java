package uk.ashleybye.httpserver.server;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.StatusCode;

public interface Response {

  void setProtocolVersion(ProtocolVersion protocolVersion);

  void setStatusCode(StatusCode statusCode);

  void addHeader(String key, String value);

  void setBody(String body);

  String serialize();
}
