package uk.ashleybye.httpserver.server.tcp;

import uk.ashleybye.httpserver.server.ClosingConnectionException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.IncomingConnectionException;
import uk.ashleybye.httpserver.server.OutgoingConnectionException;

public class ErrorClientConnectionStub implements Connection {

  private boolean receiveDataShouldThrowIncomingClientConnectionException = false;
  private boolean sendDataShouldThrowOutgoingClientConnectionException = false;
  private boolean closeShouldThrowClosingClientConnectionException = false;

  @Override
  public String receiveData() {
    if (receiveDataShouldThrowIncomingClientConnectionException) {
      throw new IncomingConnectionException(new Exception());
    } else {
      return "GET /simple_get HTTP/1.1";
    }
  }

  @Override
  public void sendData(String data) {
    if (sendDataShouldThrowOutgoingClientConnectionException) {
      throw new OutgoingConnectionException(new Exception());
    }
  }

  @Override
  public void close() {
    if (closeShouldThrowClosingClientConnectionException) {
      throw new ClosingConnectionException(new Exception());
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
