package uk.ashleybye.httpserver.server;

public interface RequestHandler {

  ResponseHandler buildRequest(String incomingData);
}
