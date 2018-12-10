package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;

public class HttpRequestStub implements Request {

  private final String method;
  private final String uri;
  private final String protocolVersion;
  private final String body;

  public HttpRequestStub(String method, String uri, String protocolVersion, String body) {
    this.method = method;
    this.uri = uri;
    this.protocolVersion = protocolVersion;
    this.body = body;
  }

  @Override
  public String getMethod() {
    return method;
  }

  @Override
  public String getUri() {
    return uri;
  }

  @Override
  public String getProtocolVersion() {
    return protocolVersion;
  }

  @Override
  public String getBody() {
    return body;
  }
}
