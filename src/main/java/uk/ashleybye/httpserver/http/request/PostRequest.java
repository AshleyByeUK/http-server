package uk.ashleybye.httpserver.http.request;

import uk.ashleybye.httpserver.http.response.HttpResponse;
import uk.ashleybye.httpserver.server.Controller;

public class PostRequest extends HttpRequest {

  @Override
  public HttpResponse respond(Controller controller) {
    HttpResponse response = new HttpResponse();
    controller.post(this, response);
    return response;
  }
}
