package uk.ashleybye.httpserver.http;

public enum RequestMethod {
  GET("GET"),
  HEAD("HEAD"),
  OPTIONS("OPTIONS"),
  POST("POST"),
  PUT("PUT");

  private final String method;

  RequestMethod(String method) {
    this.method = method;
  }

  @Override
  public String toString() {
    return method;
  }


}
