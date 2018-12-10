package uk.ashleybye.httpserver.server;

public class PortSpy implements Port {

  private final ConnectionSpy connection;
  private boolean listening = false;

  public PortSpy(ConnectionSpy connection) {
    this.connection = connection;
  }

  @Override
  public void listen(ConnectionListener connectionListener) {
    listening = true;
    connectionListener.handleConnection(connection);
  }

  @Override
  public void close() {
    listening = false;
  }

  public boolean isListening() {
    return listening;
  }
}
