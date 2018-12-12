package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.Response;

public interface Route {

  Response handleRequest(Request request, Response response);
}
