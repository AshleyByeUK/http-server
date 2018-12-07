package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.ResponseHandler;

public class HttpRequestHandlerStub implements RequestHandler {

  private final HttpResponseHandlerStub httpResponseHandlerStub;

  public HttpRequestHandlerStub(HttpResponseHandlerStub httpResponseHandlerStub) {
    this.httpResponseHandlerStub = httpResponseHandlerStub;
  }

  @Override
  public ResponseHandler buildRequest(String incomingData) {
    return httpResponseHandlerStub;
  }
}
