package uk.ashleybye.httpserver.http.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.StatusCode;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public abstract class Controller {

  private final List<RequestMethod> allowedMethods;

  public Controller(RequestMethod... allowedMethods) {
    this.allowedMethods = new ArrayList<>(Arrays.asList(allowedMethods));
    this.allowedMethods.add(RequestMethod.HEAD);
    this.allowedMethods.add(RequestMethod.OPTIONS);
  }

  public static Controller routeNotFoundController() {
    return new Controller() {
      @Override
      public void head(Request request, Response response) {
        buildNotFoundResponse(request, response);
      }

      @Override
      public void options(Request request, Response response) {
        buildNotFoundResponse(request, response);
      }

      @Override
      public void get(Request request, Response response) {
        buildNotFoundResponse(request, response);
      }

      @Override
      public void post(Request request, Response response) {
        buildNotFoundResponse(request, response);
      }

      private void buildNotFoundResponse(Request request, Response response) {
        response.setProtocolVersion(request.getProtocolVersion());
        response.setStatusCode(StatusCode.NOT_FOUND);
        response.setBody("");
      }
    };
  }

  public void head(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.OK);
    response.setBody("");
  }

  public void options(Request request, Response response) {
    generateResponseWithAllowedMethods(StatusCode.OK, request, response);
  }

  public void get(Request request, Response response) {
    generateResponseWithAllowedMethods(StatusCode.METHOD_NOT_ALLOWED, request, response);
  }

  public void post(Request request, Response response) {
    generateResponseWithAllowedMethods(StatusCode.METHOD_NOT_ALLOWED, request, response);
  }

  private void generateResponseWithAllowedMethods(StatusCode statusCode, Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(statusCode);
    response.addHeader("Allow", stringifyList(allowedMethods));
    response.setBody("");
  }

  private <T> String stringifyList(List<T> list) {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      string
          .append(currentItemAsString(i, list))
          .append(possibleTrailingComma(i, list));
    }
    return string.toString();
  }

  private <T> String currentItemAsString(int currentItemNumber, List<T> list) {
    return list.get(currentItemNumber).toString();
  }

  private <T> String possibleTrailingComma(int currentItemNumber, List<T> list) {
    return (currentItemNumber < list.size() - 1) ? "," : "";
  }
}
