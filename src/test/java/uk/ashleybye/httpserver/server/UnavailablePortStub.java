package uk.ashleybye.httpserver.server;

public class UnavailablePortStub implements Port {

  @Override
  public void listen() {
    throw new PortUnavailableException();
  }

  @Override
  public Connection acceptConnection() {
    return null;
  }

  @Override
  public void close() {
    // Do nothing.
  }
}
