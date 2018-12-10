package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestHandler;

public class HttpRequestHandler implements RequestHandler {

  @Override
  public HttpResponse handle(Request request) {
    HttpResponse response = new HttpResponse();
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(200);
    response.setStatusMessage("OK");
    response.setBody("");
    return response;
  }
}
