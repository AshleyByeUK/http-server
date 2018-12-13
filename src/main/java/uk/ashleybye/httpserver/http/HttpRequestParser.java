package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.http.request.GetRequest;
import uk.ashleybye.httpserver.http.request.HeadRequest;
import uk.ashleybye.httpserver.http.request.OptionsRequest;
import uk.ashleybye.httpserver.http.request.PostRequest;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestParser;

public class HttpRequestParser implements RequestParser {

  @Override
  public Request parse(String incomingData) {
    String[] headersAndBody = incomingData.split("\n\r\n");
    String[] headers = headersAndBody.length > 0 ? headersAndBody[0].split("\n") : new String[]{};
    String statusLine = headers[0].strip();
    String method = statusLine.split(" ")[0];
    String uri = statusLine.split(" ")[1];
    String protocolVersion = statusLine.split(" ")[2];
    String body = headersAndBody.length > 1 ? headersAndBody[1] : "";

    Request request;
    RequestMethod requestMethod = parseMethod(method);
    if (requestMethod.equals(RequestMethod.HEAD)) {
      request = new HeadRequest();
    } else if (requestMethod.equals(RequestMethod.OPTIONS)) {
      request = new OptionsRequest();
    } else if (requestMethod.equals(RequestMethod.GET)) {
      request = new GetRequest();
    } else {
      request = new PostRequest();
    }
    request.setMethod(parseMethod(method));
    request.setUri(uri);
    request.setProtocolVersion(parseProtocolVersion(protocolVersion));
    request.setBody(body);
    return request;
  }

  private RequestMethod parseMethod(String method) {
    switch (method) {
      case "HEAD":
        return RequestMethod.HEAD;
      case "OPTIONS":
        return RequestMethod.OPTIONS;
      case "GET":
        return RequestMethod.GET;
      case "POST":
        return RequestMethod.POST;
      default:
        return RequestMethod.INVALID_METHOD;
    }
  }

  private ProtocolVersion parseProtocolVersion(String protocolVersion) {
    if (protocolVersion.equals("HTTP/1.1")) {
      return ProtocolVersion.HTTP_1_1;
    } else {
      return ProtocolVersion.NOT_SUPPORTED;
    }
  }
}
