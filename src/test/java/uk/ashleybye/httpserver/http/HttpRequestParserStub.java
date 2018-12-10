package uk.ashleybye.httpserver.http;

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
