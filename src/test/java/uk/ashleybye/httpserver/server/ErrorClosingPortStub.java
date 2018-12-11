package uk.ashleybye.httpserver.server;

public class ErrorClosingPortStub implements Port {

  @Override
  public void listen(Server server) {
    server.handleConnection(new ConnectionSpy("GET / HTTP/1.1"));
  }

  @Override
  public void close() {
    throw new ClosingPortException();
  }

  @Override
  public boolean isClosed() {
    return true;
  }
}
