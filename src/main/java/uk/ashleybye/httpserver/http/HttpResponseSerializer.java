package uk.ashleybye.httpserver.http;

public class HttpResponseSerializer implements ResponseSerializer {

  @Override
  public String serialize(Response response) {
    return String.format("%s %d %s\n%s",
        response.getProtocolVersion(),
        response.getStatusCode(),
        response.getStatusMessage(),
        response.getBody());
  }
}
