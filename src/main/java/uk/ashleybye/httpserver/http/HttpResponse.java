package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Response;

public class HttpResponse implements Response {

  private ProtocolVersion protocolVersion;
  private StatusCode statusCode;
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
  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String serialize() {
    return String.format("%s %d %s\n%s",
        protocolVersion.toString(),
        statusCode.getStatusCode(),
        statusCode.getStatusMessage(),
        body);
  }
}
