package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.RequestParser;
import uk.ashleybye.httpserver.server.ResponseSerializer;

public class HttpRequestHandler implements RequestHandler {

  private final RequestParser requestParser;
  private final ResponseSerializer responseSerializer;

  public HttpRequestHandler(RequestParser requestParser, ResponseSerializer responseSerializer) {
    this.requestParser = requestParser;
    this.responseSerializer = responseSerializer;
  }

  @Override
  public String handle(String message) {
    Request request = requestParser.parse(message);
    HttpResponse response = handleRequest(request);
    return responseSerializer.serialize(response);
  }

  private HttpResponse handleRequest(Request request) {
    HttpResponse response = new HttpResponse();
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(200);
    response.setStatusMessage("OK");
    response.setBody("");
    return response;
  }
}
