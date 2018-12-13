package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.http.controller.Controller;
import uk.ashleybye.httpserver.http.request.GetRequest;
import uk.ashleybye.httpserver.http.request.HeadRequest;
import uk.ashleybye.httpserver.http.request.HttpRequest;
import uk.ashleybye.httpserver.http.request.OptionsRequest;
import uk.ashleybye.httpserver.http.request.PostRequest;
import uk.ashleybye.httpserver.http.response.BadRequestResponse;
import uk.ashleybye.httpserver.http.response.HttpVersionNotSupportedResponse;
import uk.ashleybye.httpserver.http.response.NotImplementedResponse;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestParser;
import uk.ashleybye.httpserver.server.Response;

public class HttpRequestParser implements RequestParser {

  @Override
  public Request parse(String incomingData) {
    try {
      return parseIncomingData(incomingData);
    } catch (NotImplementedException e) {
      return new HttpRequest() {
        @Override
        public Response respond(Controller controller) {
          return new NotImplementedResponse();
        }
      };
    } catch (UnsupportedProtocolVersionException e) {
      return new HttpRequest() {
        @Override
        public Response respond(Controller controller) {
          return new HttpVersionNotSupportedResponse();
        }
      };
    } catch (Exception e) {
      return new HttpRequest() {
        @Override
        public Response respond(Controller controller) {
          return new BadRequestResponse();
        }
      };
    }
  }

  private Request parseIncomingData(String incomingData) {
    Request request;
    String statusLine = parseStatusLine(incomingData);

    String method = statusLine.split(" ")[0];
    RequestMethod requestMethod = parseMethod(method);
    request = newRequestObjectForMethod(requestMethod);
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

  private Request newRequestObjectForMethod(RequestMethod requestMethod) {
    Request request;
    switch (requestMethod) {
      case HEAD:
        request = new HeadRequest();
        break;
      case OPTIONS:
        request = new OptionsRequest();
        break;
      case GET:
        request = new GetRequest();
        break;
      case POST:
        request = new PostRequest();
        break;
      default:
        throw new NotImplementedException();
    }
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
