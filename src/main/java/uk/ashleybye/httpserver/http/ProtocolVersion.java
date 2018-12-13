package uk.ashleybye.httpserver.http;

public enum ProtocolVersion {
  HTTP_1_1("HTTP/1.1");

  private String version;

  ProtocolVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return version;
  }
}
