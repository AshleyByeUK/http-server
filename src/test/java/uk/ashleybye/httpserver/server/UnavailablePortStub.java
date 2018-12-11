package uk.ashleybye.httpserver.server;

public class UnavailablePortStub implements Port {

  @Override
  public void listen(Server server) {
    throw new PortUnavailableException();
  }

  @Override
  public void close() {
    // Do nothing.
  }

  @Override
  public boolean isClosed() {
    return false;
  }
}
