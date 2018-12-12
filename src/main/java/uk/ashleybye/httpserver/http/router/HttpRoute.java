package uk.ashleybye.httpserver.http.router;

import java.util.ArrayList;
import java.util.List;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.Route;
import uk.ashleybye.httpserver.http.StatusCode;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class HttpRoute implements Route {

  private final Controller controller;
  private final List<RequestMethod> allowedMethods;

  public HttpRoute(Controller controller, List<RequestMethod> allowedMethods) {
    this.controller = controller;
    this.allowedMethods = new ArrayList<>(allowedMethods);
    this.allowedMethods.add(RequestMethod.HEAD);
    this.allowedMethods.add(RequestMethod.OPTIONS);
  }

  public static Route notFoundRoute() {
    return (request, response) -> {
      response.setProtocolVersion(request.getProtocolVersion());
      response.setStatusCode(StatusCode.NOT_FOUND);
      response.setBody("");
      return response;
    };
  }

  @Override
  public Response handleRequest(Request request, Response response) {
    if (request.getMethod().equals(RequestMethod.HEAD)) {
      response.setProtocolVersion(request.getProtocolVersion());
      response.setStatusCode(StatusCode.OK);
      response.setBody("");
    } else if (request.getMethod().equals(RequestMethod.OPTIONS)) {
      response.setProtocolVersion(request.getProtocolVersion());
      response.setStatusCode(StatusCode.OK);
      response.addHeader("Allow", stringifyList(allowedMethods));
      response.setBody("");
    } else if (request.getMethod().equals(RequestMethod.GET) && allowedMethods.contains(RequestMethod.GET)) {
      controller.get(request, response);
    }
    return response;
  }

  private <T> String stringifyList(List<T> list) {
    String string = "";
    for (int i = 0; i < list.size(); i++) {
      string += list.get(i).toString();
      if (i < list.size() - 1) {
        string += ",";
      }
    }
    return string;
  }
}
