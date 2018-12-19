package uk.ashleybye.httpserver.server;

public class OutgoingConnectionException extends RuntimeException {

  public OutgoingConnectionException(Throwable cause) {
    super(cause);
  }
}
