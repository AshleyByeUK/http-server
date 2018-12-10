package uk.ashleybye.httpserver.server;

public class MultipleConnectionsSpy implements Connection {

  private static int numberOfTimesReceivedData = 0;

  private final String incomingData;
  private String data;

  public MultipleConnectionsSpy(String incomingData) {
    this.incomingData = incomingData;
  }

  @Override
  public String receiveData() {
    numberOfTimesReceivedData++;
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

  public int getNumberOfTimesReceivedData() {
    return numberOfTimesReceivedData;
  }
}
