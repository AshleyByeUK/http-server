package uk.ashleybye.httpserver.http;

public class HttpRequestParser implements RequestParser {

  @Override
  public HttpRequest parse(String incomingData) {
    String[] headersAndBody = incomingData.split("\n\n");
    String headers = headersAndBody.length > 0 ? headersAndBody[0] : "";
    String method = headers.split(" ")[0];
    String uri = headers.split(" ")[1];
    String protocolVersion = headers.split(" ")[2];
    String body = headersAndBody.length > 1 ? headersAndBody[1] : "";

    HttpRequest request = new HttpRequest();
    request.setMethod(method);
    request.setUri(uri);
    request.setProtocolVersion(protocolVersion);
    request.setBody(body);
    return request;
  }
}
