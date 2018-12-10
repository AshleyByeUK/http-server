package uk.ashleybye.httpserver.server;

public interface ConnectionListener {

  void listenForConnections();

  void handleConnection(Connection connection);

  void stopListeningForConnections();
}
