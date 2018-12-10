package uk.ashleybye.httpserver.server;

public class UnavailablePortStub implements Port {

  @Override
  public void listen(ConnectionListener connectionListener) {
    throw new PortUnavailableException();
  }

  @Override
  public void close() {
    // Do nothing.
  }

  @Override
  public boolean isContinuingListening() {
    return true;
  }
}
