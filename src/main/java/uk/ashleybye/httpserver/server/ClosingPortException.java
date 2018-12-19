package uk.ashleybye.httpserver.server;

public class ClosingPortException extends RuntimeException {

  public ClosingPortException(Throwable cause) {
    super(cause);
  }
}
