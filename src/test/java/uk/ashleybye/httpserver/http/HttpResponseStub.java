package uk.ashleybye.httpserver.http;

public class HttpResponseStub implements Response {

  private final String protocolVersion;
  private final int statusCode;
  private final String statusMessage;
  private final String body;

  public HttpResponseStub(String protocolVersion, int statusCode, String statusMessage, String body) {
    this.protocolVersion = protocolVersion;
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
    this.body = body;
  }

  @Override
  public String getProtocolVersion() {
    return protocolVersion;
  }

  @Override
  public void setProtocolVersion(String protocolVersion) {
    // Do nothing.
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  @Override
  public void setStatusCode(int statusCode) {
    // Do nothing.
  }

  @Override
  public String getStatusMessage() {
    return statusMessage;
  }

  @Override
  public void setStatusMessage(String statusMessage) {
    // Do nothing.
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public void setBody(String body) {
    // Do nothing.
  }
}
