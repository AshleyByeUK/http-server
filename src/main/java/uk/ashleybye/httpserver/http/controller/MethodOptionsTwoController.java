package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;

public class MethodOptionsTwoController extends Controller {

  public MethodOptionsTwoController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
