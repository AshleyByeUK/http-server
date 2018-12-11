package uk.ashleybye.httpserver.server;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class PortSpy implements Port {

  private final Deque<Connection> connections = new ArrayDeque<>();
  private static int numberOfTimesListenCalled = 0;
  private boolean closed = false;

  public PortSpy(Connection... connections) {
    this.connections.addAll(Arrays.asList(connections));
  }

  @Override
  public void listen(Server server) {
    if (connections.size() != 0) {
      numberOfTimesListenCalled++;
      server.handleConnection(connections.removeFirst());
    } else {
      close();
    }
  }

  @Override
  public void close() {
    if (connections.size() == 0) {
      closed = true;
    }
  }

  @Override
  public boolean isClosed() {
    return closed;
  }

  public int getNumberOfTimesListenCalled() {
    return numberOfTimesListenCalled;
  }

  public void resetNumberOfTimesListenCalled() {
    numberOfTimesListenCalled = 0;
  }
}
