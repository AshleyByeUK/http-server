package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;

public class GetWithBodyController extends Controller {

  public GetWithBodyController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
