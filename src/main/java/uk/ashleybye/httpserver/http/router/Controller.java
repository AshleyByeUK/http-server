package uk.ashleybye.httpserver.http.router;

import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public interface Controller {

  void get(Request request, Response response);
}
