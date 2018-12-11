package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.RequestHandler;

public class HttpRequestHandler implements RequestHandler {

  private final RequestParser parser;
  private final ResponseSerializer serializer;
  private final HttpServer server;

  public HttpRequestHandler(RequestParser parser, ResponseSerializer serializer, HttpServer server) {
    this.parser = parser;
    this.serializer = serializer;
    this.server = server;
  }

  @Override
  public String handle(String message) {
    Request request = parser.parse(message);
    Response response = new HttpResponse();
    if (server.hasRoute(request.getUri())) {
      handleRequest(request, response);
    } else {
      handleNotFound(request, response);
    }
    return serializer.serialize(response);
  }

  private void handleRequest(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(200);
    response.setStatusMessage("OK");
    response.setBody("");
  }

  private void handleNotFound(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(404);
    response.setStatusMessage("Not Found");
    response.setBody("");
  }
}
