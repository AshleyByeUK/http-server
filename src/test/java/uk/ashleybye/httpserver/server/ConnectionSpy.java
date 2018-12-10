package uk.ashleybye.httpserver.server;

public class ConnectionSpy implements Connection {

  private final String incomingData;
  private String data;

  public ConnectionSpy(String incomingData) {
    this.incomingData = incomingData;
  }

  @Override
  public String receiveData() {
    return incomingData;
  }

  @Override
  public void sendData(String data) {
    this.data = data;
  }

  @Override
  public void close() {

  }

  public String getSentData() {
    return data;
  }
}
