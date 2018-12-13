package uk.ashleybye.httpserver.http;

public enum StatusCode {
  BAD_REQUEST {
    @Override
    public int getStatusCode() {
      return 400;
    }

    @Override
    public String getStatusMessage() {
      return "Bad Request";
    }
  },

  HTTP_VERSION_NOT_SUPPORTED {
    @Override
    public int getStatusCode() {
      return 505;
    }

    @Override
    public String getStatusMessage() {
      return "HTTP Version Not Supported";
    }
  },

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

  NOT_IMPLEMENTED {
    public int getStatusCode() {
      return 501;
    }

    public String getStatusMessage() {
      return "Not Implemented";
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
