package uk.ashleybye.httpserver.server;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class PortSpy implements Port {

  private final Deque<Connection> connections = new ArrayDeque<>();
  private final Server server;
  private int numberOfTimesAcceptConnectionCalled = 0;
  private boolean closed = false;

  public PortSpy(Server server, Connection... connections) {
    this.server = server;
    this.connections.addAll(Arrays.asList(connections));
  }

  @Override
  public void listen() {
    // Do nothing.
  }

  @Override
  public Connection acceptConnection() {
    if (connections.size() == 1) {
      server.stop();
    }

    numberOfTimesAcceptConnectionCalled++;
    return connections.removeFirst();
  }

  @Override
  public void close() {
    closed = true;
  }

  public boolean isClosed() {
    return closed;
  }

  public int getNumberOfTimesAcceptConnectionCalled() {
    return numberOfTimesAcceptConnectionCalled;
  }
}
