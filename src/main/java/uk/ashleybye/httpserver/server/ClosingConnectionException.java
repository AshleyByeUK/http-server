package uk.ashleybye.httpserver.server;

public class ClosingConnectionException extends RuntimeException {

  public ClosingConnectionException(Throwable cause) {
    super(cause);
  }
}
