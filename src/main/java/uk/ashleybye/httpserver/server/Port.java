package uk.ashleybye.httpserver.server;

public interface Port {

  void listen(ConnectionListener connectionListener);

  void close();

  boolean isContinuingListening();
}
