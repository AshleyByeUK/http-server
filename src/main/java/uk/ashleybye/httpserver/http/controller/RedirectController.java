package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public class RedirectController extends Controller {

  public RedirectController(RequestMethod... requestMethods) {
    super(requestMethods);
  }

  @Override
  public void get(Request request, Response response) {
    redirect("http://0.0.0.0:5000/simple_get", request, response);
  }
}
