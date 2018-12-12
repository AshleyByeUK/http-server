package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.router.Controller;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class MethodOptionsController extends Controller {

  public MethodOptionsController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }

  @Override
  public void get(Request request, Response response) {
    // Do nothing.
  }
}
