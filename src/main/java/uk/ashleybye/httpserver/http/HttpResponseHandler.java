package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.ResponseHandler;

public class HttpResponseHandler implements ResponseHandler {

  @Override
  public String serializeResponse() {
    return "HTTP/1.1 200 OK\n";
  }
}
