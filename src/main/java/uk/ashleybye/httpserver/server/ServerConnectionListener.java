package uk.ashleybye.httpserver.server;

public class ServerConnectionListener implements ConnectionListener {

  private final Port port;
  private final ConnectionHandler connectionHandler;

  public ServerConnectionListener(Port port, ConnectionHandler connectionHandler) {
    this.port = port;
    this.connectionHandler = connectionHandler;
  }

  @Override
  public void listenForConnections() {
    port.listen(this);
  }

  @Override
  public void handleConnection(Connection connection) {
    connectionHandler.handleConnection(connection);
  }

  @Override
  public void stopListeningForConnections() {
    port.close();
  }
}
