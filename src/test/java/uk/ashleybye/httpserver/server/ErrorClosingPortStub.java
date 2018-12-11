package uk.ashleybye.httpserver.server;

public class ErrorClosingPortStub implements Port {

  @Override
  public void listen(ConnectionListener connectionListener) {
    connectionListener.handleConnection(new ConnectionSpy("GET / HTTP/1.1"));
  }

  @Override
  public void close() {
    throw new ClosingServerPortException();
  }

  @Override
  public boolean isContinuingListening() {
    return true;
  }
}
