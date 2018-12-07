package uk.ashleybye.httpserver;

import uk.ashleybye.httpserver.server.ClosingServerPortException;
import uk.ashleybye.httpserver.server.ConnectionListener;
import uk.ashleybye.httpserver.server.ConnectionSpy;
import uk.ashleybye.httpserver.server.Port;

public class ErrorClosingPortStub implements Port {

  @Override
  public void listen(ConnectionListener connectionListener) {
    connectionListener.handleConnection(new ConnectionSpy(""));
  }

  @Override
  public void close() {
    throw new ClosingServerPortException();
  }
}
