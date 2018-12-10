package uk.ashleybye.httpserver.server;

public interface ConnectionHandler {

  void handleConnection(Connection connection);
}
