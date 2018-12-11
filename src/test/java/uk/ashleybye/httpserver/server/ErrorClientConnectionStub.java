package uk.ashleybye.httpserver.server;

public class ErrorClientConnectionStub implements Connection {

  private boolean receiveDataShouldThrowIncomingClientConnectionException = false;
  private boolean sendDataShouldThrowOutgoingClientConnectionException = false;
  private boolean closeShouldThrowClosingClientConnectionException = false;

  @Override
  public String receiveData() {
    if (receiveDataShouldThrowIncomingClientConnectionException) {
      throw new IncomingConnectionException();
    } else {
      return "GET /simple_get HTTP/1.1";
    }
  }

  @Override
  public void sendData(String data) {
    if (sendDataShouldThrowOutgoingClientConnectionException) {
      throw new OutgoingConnectionException();
    }
  }

  @Override
  public void close() {
    if (closeShouldThrowClosingClientConnectionException) {
      throw new ClosingConnectionException();
    }
  }

  public void receiveDataShouldThrowIncomingConnectionException() {
    receiveDataShouldThrowIncomingClientConnectionException = true;
  }

  public void sendDataShouldThrowOutgoingConnectionException() {
    sendDataShouldThrowOutgoingClientConnectionException = true;
  }

  public void closeShouldThrowClosingConnectionException() {
    closeShouldThrowClosingClientConnectionException = true;
  }
}
