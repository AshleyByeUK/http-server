package uk.ashleybye.httpserver.http;

public enum StatusCode {
  NOT_FOUND {
    public int getStatusCode() {
      return 404;
    }

    public String getStatusMessage() {
      return "Not Found";
    }
  },

  OK {
    public int getStatusCode() {
      return 200;
    }

    public String getStatusMessage() {
      return "OK";
    }
  };

  public abstract int getStatusCode();

  public abstract String getStatusMessage();
}
