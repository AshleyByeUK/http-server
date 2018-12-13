package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;

public class MethodOptionsController extends Controller {

  public MethodOptionsController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
