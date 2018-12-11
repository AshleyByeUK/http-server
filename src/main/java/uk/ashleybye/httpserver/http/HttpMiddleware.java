package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Middleware;

public class HttpMiddleware implements Middleware {

  private final RequestParser parser;
  private final Router router;
  private final ResponseSerializer serializer;

  public HttpMiddleware(RequestParser parser, Router router, ResponseSerializer serializer) {
    this.parser = parser;
    this.router = router;
    this.serializer = serializer;
  }

  @Override
  public String processMessage(String message) {
    Request request = parser.parse(message);
    Response response = router.route(request);
    return serializer.serialize(response);
  }
}
