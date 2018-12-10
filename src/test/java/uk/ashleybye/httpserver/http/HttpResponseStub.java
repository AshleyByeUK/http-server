package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Response;

public class HttpResponseStub implements Response {

  private final String protocolVersion;
  private final int statusCode;
  private final String statuMessage;
  private final String body;

  public HttpResponseStub(String protocolVersion, int statusCode, String statuMessage, String body) {
    this.protocolVersion = protocolVersion;
    this.statusCode = statusCode;
    this.statuMessage = statuMessage;
    this.body = body;
  }

  @Override
  public String getProtocolVersion() {
    return protocolVersion;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  @Override
  public String getStatusMessage() {
    return statuMessage;
  }

  @Override
  public String getBody() {
    return body;
  }
}
