package uk.ashleybye.httpserver.server;

public interface RequestParser {

  Request parse(String incomingData);
}
