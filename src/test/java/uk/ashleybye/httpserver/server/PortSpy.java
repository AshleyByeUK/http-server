package uk.ashleybye.httpserver.server;

public class PortSpy implements Port {

  private final Connection connection;
  private boolean listening = false;
  private int numberOfConnectionsToSimulate = 1;
  private int numberOfConnectionsSimulated = 0;

  public PortSpy(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void listen(ConnectionListener connectionListener) {
    listening = true;
    numberOfConnectionsSimulated++;
    connectionListener.handleConnection(connection);
  }

  @Override
  public void close() {
    listening = false;
  }

  @Override
  public boolean isContinuingListening() {
    return numberOfConnectionsSimulated < numberOfConnectionsToSimulate;
  }

  public boolean isListening() {
    return listening;
  }

  public void setNumberOfConnectionsToSimulate(int connections) {
    numberOfConnectionsToSimulate = connections;
  }
}
