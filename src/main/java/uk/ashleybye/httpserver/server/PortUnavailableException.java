package uk.ashleybye.httpserver.server;

public class PortUnavailableException extends RuntimeException {

  public PortUnavailableException(Throwable cause) {
    super(cause);
  }
}
