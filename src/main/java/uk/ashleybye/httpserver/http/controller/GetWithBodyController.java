package uk.ashleybye.httpserver.http.controller;

import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.router.Controller;

public class GetWithBodyController extends Controller {

  public GetWithBodyController(RequestMethod... allowedMethods) {
    super(allowedMethods);
  }
}
