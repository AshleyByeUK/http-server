package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.ResponseHandler;

public class HttpRequestHandler implements RequestHandler {

  @Override
  public ResponseHandler buildRequest(String incomingData) {
    return new HttpResponseHandler();
  }
}
