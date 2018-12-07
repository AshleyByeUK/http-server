package uk.ashleybye.httpserver.server;

public class ConnectionHandlerSpy implements ConnectionHandler {

  private Connection connection;

  @Override
  public void handleConnection(Connection connection) {
    this.connection = connection;
  }

  Connection getHandledConnection() {
    return connection;
  }
}
