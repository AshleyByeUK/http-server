package uk.ashleybye.httpserver.http;

public class HttpResponse implements Response {

  private String protocolVersion;
  private int statusCode;
  private String statusMessage;
  private String body;

  @Override
  public String getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  @Override
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
