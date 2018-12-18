package uk.ashleybye.httpserver.http.request;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Responder;
import uk.ashleybye.httpserver.server.Response;

public class HttpRequest implements Request {

  private RequestMethod method;
  private String uri;
  private ProtocolVersion protocolVersion;
  private String body;

  @Override
  public void respond(Responder controller, Response response) {
    controller.respond(this, response);
  }

  @Override
  public boolean hasParseError() {
    return false;
  }

  @Override
  public RequestMethod getMethod() {
    return method;
  }

  public void setMethod(RequestMethod method) {
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
  public ProtocolVersion getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(ProtocolVersion protocolVersion) {
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
