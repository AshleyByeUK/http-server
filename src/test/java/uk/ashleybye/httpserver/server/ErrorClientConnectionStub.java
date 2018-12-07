package uk.ashleybye.httpserver.server;

public class ErrorClientConnectionStub implements Connection {

  private boolean receiveDataShouldThrowIncomingClientConnectionException = false;
  private boolean sendDataShouldThrowOutgoingClientConnectionException = false;
  private boolean closeShouldThrowClosingClientConnectionException = false;

  @Override
  public String receiveData() {
    if (receiveDataShouldThrowIncomingClientConnectionException) {
      throw new IncomingClientConnectionException();
    } else {
      return "";
    }
  }

  @Override
  public void sendData(String data) {
    if (sendDataShouldThrowOutgoingClientConnectionException) {
      throw new OutgoingClientConnectionException();
    }
  }

  @Override
  public void close() {
    if (closeShouldThrowClosingClientConnectionException) {
      throw new ClosingClientConnectionException();
    }
  }

  public void receiveDataShouldThrowIncomingClientConnectionException() {
    receiveDataShouldThrowIncomingClientConnectionException = true;
  }

  public void sendDataShouldThrowOutgoingClientConnectionException() {
    sendDataShouldThrowOutgoingClientConnectionException = true;
  }

  public void closeShouldThrowClosingClientConnectionException() {
    closeShouldThrowClosingClientConnectionException = true;
  }
}
