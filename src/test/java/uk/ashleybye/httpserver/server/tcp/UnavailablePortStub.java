package uk.ashleybye.httpserver.server.tcp;

import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.PortUnavailableException;

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
