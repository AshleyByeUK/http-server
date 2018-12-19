package uk.ashleybye.httpserver.server.tcp;

import uk.ashleybye.httpserver.server.ClosingPortException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.Server;

public class ErrorClosingPortStub implements Port {

  private final Server server;

  public ErrorClosingPortStub(Server server) {
    this.server = server;
  }

  @Override
  public void listen() {
    // Do nothing.
  }

  @Override
  public Connection acceptConnection() {
    server.stop();
    return new ConnectionSpy("GET / HTTP/1.1");
  }

  @Override
  public void close() {
    throw new ClosingPortException(new Exception());
  }
}
