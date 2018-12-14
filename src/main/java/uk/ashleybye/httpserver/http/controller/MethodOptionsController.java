package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;

public class MethodOptionsController extends HttpController {

  public MethodOptionsController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
