package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.StatusCode;
import uk.ashleybye.httpserver.http.router.Controller;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class GetWithBodyController implements Controller {

  @Override
  public void get(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.OK);
    response.setBody("body");
  }

  @Override
  public void head(Request request, Response response) {
    response.setProtocolVersion(request.getProtocolVersion());
    response.setStatusCode(StatusCode.OK);
    response.setBody("");
  }
}
