package uk.ashleybye.httpserver.server;

public interface Port {

  void listen();

  Connection acceptConnection();

  void close();
}
