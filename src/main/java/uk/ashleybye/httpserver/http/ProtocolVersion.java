package uk.ashleybye.httpserver.http;

public enum ProtocolVersion {
  HTTP_1_1("HTTP/1.1"),
  NOT_SUPPORTED("HTTP version not supported");

  private String protocolVersion;

  ProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  @Override
  public String toString() {
    return protocolVersion;
  }
}
