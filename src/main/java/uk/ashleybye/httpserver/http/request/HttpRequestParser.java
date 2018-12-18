package uk.ashleybye.httpserver.http.request;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.server.RequestParser;

public class HttpRequestParser implements RequestParser {

  @Override
  public HttpRequest parse(String incomingData) {
    try {
      return parseIncomingData(incomingData);
    } catch (NotImplementedException e) {
      return new NotImplementedRequest();
    } catch (UnsupportedProtocolVersionException e) {
      return new UnsupportedProtocolRequest();
    } catch (Exception e) {
      return new BadRequest();
    }
  }

  private HttpRequest parseIncomingData(String incomingData) {
    HttpRequest request = new HttpRequest();
    String statusLine = parseStatusLine(incomingData);

    String method = statusLine.split(" ")[0];
    RequestMethod requestMethod = parseMethod(method);
    request.setMethod(requestMethod);

    String uri = statusLine.split(" ")[1];
    request.setUri(uri);

    String protocolVersion = statusLine.split(" ")[2];
    request.setProtocolVersion(parseProtocolVersion(protocolVersion));

    String body = parseBody(incomingData);
    request.setBody(body);

    return request;
  }

  private String parseStatusLine(String incomingData) {
    String[] headersAndBody = incomingData.split("\n\r\n");
    String[] headers = headersAndBody.length > 0 ? headersAndBody[0].split("\n") : new String[]{};
    return headers[0].strip();
  }

  private String parseBody(String incomingData) {
    String[] headersAndBody = incomingData.split("\n\r\n");
    return headersAndBody.length > 1 ? headersAndBody[1] : "";
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
        throw new NotImplementedException();
    }
  }

  private ProtocolVersion parseProtocolVersion(String protocolVersion) {
    if (protocolVersion.equals("HTTP/1.1")) {
      return ProtocolVersion.HTTP_1_1;
    } else {
      throw new UnsupportedProtocolVersionException();
    }
  }

  private class NotImplementedException extends RuntimeException {

  }

  private class UnsupportedProtocolVersionException extends RuntimeException {

  }
}
