package uk.ashleybye.httpserver.http;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.httpserver.server.Response;

public class HttpResponse implements Response {

  private ProtocolVersion protocolVersion;
  private StatusCode statusCode;
  private Map<String, String> headers = new HashMap<>();
  private String body;

  @Override
  public void setProtocolVersion(ProtocolVersion protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  @Override
  public void setStatusCode(StatusCode statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public void addHeader(String key, String value) {
    headers.putIfAbsent(key, value);
  }

  @Override
  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String serialize() {
    return serializedStatusLine()
        + serializedHeaders()
        + serializedBody();
  }

  private String serializedStatusLine() {
    return String.format("%s %d %s\n",
        protocolVersion.toString(),
        statusCode.getStatusCode(),
        statusCode.getStatusMessage());
  }

  private String serializedHeaders() {
    String string = "";
    for (String key : headers.keySet()) {
      string += String.format("%s: %s%n", key, headers.get(key));
    }
    return string;
  }

  private String serializedBody() {
    return body.isEmpty() ? body : "\n" + body;
  }
}
