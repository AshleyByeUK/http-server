package uk.ashleybye.httpserver.http.request;

import uk.ashleybye.httpserver.http.response.HttpResponse;
import uk.ashleybye.httpserver.http.controller.Controller;
import uk.ashleybye.httpserver.server.Response;

public class HeadRequest extends HttpRequest {

  @Override
  public Response respond(Controller controller) {
    HttpResponse response = new HttpResponse();
    controller.head(this, response);
    return response;
  }
}