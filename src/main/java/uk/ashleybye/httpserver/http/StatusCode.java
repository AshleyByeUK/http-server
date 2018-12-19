package uk.ashleybye.httpserver.http;

public enum StatusCode {
  OK(200, "OK"),

  MOVED_PERMANENTLY(301, "Moved Permanently"),

  BAD_REQUEST(400, "Bad Request"),
  NOT_FOUND(404, "Not Found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

  NOT_IMPLEMENTED(501, "Not Implemented"),
  HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");

  private int code;
  private String message;

  StatusCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getStatusCode() {
    return code;
  }

  public String getStatusMessage() {
    return message;
  }
}
