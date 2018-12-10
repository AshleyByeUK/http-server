package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;

public class HttpRequest implements Request {

  private String method;
  private String uri;
  private String protocolVersion;
  private String body;

  @Override
  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  @Override
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public String getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  @Override
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
