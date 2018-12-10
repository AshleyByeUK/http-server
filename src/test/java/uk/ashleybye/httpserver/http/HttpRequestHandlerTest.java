package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.SimpleHttpServerStub;

class HttpRequestHandlerTest {

  @Test
  void testSimpleGetRequestProducesCorrectResponse() {
    HttpServer server = new SimpleHttpServerStub("GET /simple_get");
    HttpRequestHandler requestHandler = new HttpRequestHandler(new HttpRequestParser(), new HttpResponseSerializer(),
        server);

    String request = "GET /simple_get HTTP/1.1\n\n";
    String response = requestHandler.handle(request);

    assertEquals("HTTP/1.1 200 OK\n", response);
  }

  @Test
  void testUnknownUriProduces404NotFoundResponse() {
    HttpServer server = new SimpleHttpServerStub("GET /simple_get");
    HttpRequestHandler requestHandler = new HttpRequestHandler(new HttpRequestParser(), new HttpResponseSerializer(),
        server);

    String request = "GET /not_found_resource HTTP/1.1\n\n";
    String response = requestHandler.handle(request);

    assertEquals("HTTP/1.1 404 Not Found\n", response);
  }
}
