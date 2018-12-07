package uk.ashleybye.httpserver.server;

public interface Connection {

  String receiveData();

  void sendData(String data);

  void close();
}
