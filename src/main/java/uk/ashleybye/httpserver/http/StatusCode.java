package uk.ashleybye.httpserver.http;

public enum StatusCode {
  METHOD_NOT_ALLOWED {
    @Override
    public int getStatusCode() {
      return 405;
    }

    @Override
    public String getStatusMessage() {
      return "Method Not Allowed";
    }
  },

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
