package uk.ashleybye.httpserver.server;

public interface Port {

  void listen(Server server);

  void close();

  boolean isClosed();
}
