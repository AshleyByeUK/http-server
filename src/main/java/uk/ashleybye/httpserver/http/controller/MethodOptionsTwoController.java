package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;

public class MethodOptionsTwoController extends HttpController {

  public MethodOptionsTwoController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
