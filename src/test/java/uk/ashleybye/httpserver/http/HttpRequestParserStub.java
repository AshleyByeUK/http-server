package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestParser;

public class HttpRequestParserStub implements RequestParser {

  private final HttpRequestStub requestStub;

  public HttpRequestParserStub(HttpRequestStub requestStub) {
    this.requestStub = requestStub;
  }

  @Override
  public Request parse(String incomingData) {
    return requestStub;
  }
}
