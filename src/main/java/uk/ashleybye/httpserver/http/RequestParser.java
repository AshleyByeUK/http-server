package uk.ashleybye.httpserver.http;

public interface RequestParser {

  Request parse(String incomingData);
}
