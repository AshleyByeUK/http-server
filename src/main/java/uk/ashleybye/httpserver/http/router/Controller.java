package uk.ashleybye.httpserver.http.router;

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

  public void get(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
    response.addHeader("Allow", stringifyList(allowedMethods));
    response.setBody("");
  }

  public final void head(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.OK);
    response.setBody("");
  }

  public final void options(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.OK);
    response.addHeader("Allow", stringifyList(allowedMethods));
    response.setBody("");
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
