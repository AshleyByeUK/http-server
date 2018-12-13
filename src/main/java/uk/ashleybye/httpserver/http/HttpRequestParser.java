package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.http.request.GetRequest;
import uk.ashleybye.httpserver.http.request.HeadRequest;
import uk.ashleybye.httpserver.http.request.OptionsRequest;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestParser;

public class HttpRequestParser implements RequestParser {

  @Override
  public Request parse(String incomingData) {
    String[] headersAndBody = incomingData.split("\n\n");
    String headers = headersAndBody.length > 0 ? headersAndBody[0] : "";
    String method = headers.split(" ")[0];
    String uri = headers.split(" ")[1];
    String protocolVersion = headers.split(" ")[2];
    String body = headersAndBody.length > 1 ? headersAndBody[1] : "";

    Request request;
    RequestMethod requestMethod = parseMethod(method);
    if (requestMethod.equals(RequestMethod.GET)) {
      request = new GetRequest();
    } else if (requestMethod.equals(RequestMethod.HEAD)) {
      request = new HeadRequest();
    } else {
      request = new OptionsRequest();
    }
    request.setMethod(parseMethod(method));
    request.setUri(uri);
    request.setProtocolVersion(parseProtocolVersion(protocolVersion));
    request.setBody(body);
    return request;
  }

  private RequestMethod parseMethod(String method) {
    switch (method) {
      case "GET":
        return RequestMethod.GET;
      case "HEAD":
        return RequestMethod.HEAD;
      case "OPTIONS":
        return RequestMethod.OPTIONS;
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
