package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HttpRequestHandlerTest {

  @Test
  void testSimpleGetRequestProducesCorrectResponse() {
    String request = "GET /simple_get HTTP/1.1\n\n";
    HttpRequestHandler requestHandler = new HttpRequestHandler(new HttpRequestParser(), new HttpResponseSerializer());

    String response = requestHandler.handle(request);

    assertEquals("HTTP/1.1 200 OK\n", response);
  }
}
