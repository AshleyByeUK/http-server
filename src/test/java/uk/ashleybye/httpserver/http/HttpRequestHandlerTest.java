package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.server.Response;

class HttpRequestHandlerTest {

  @Test
  void testSimpleGetRequestProduceCorrectResponse() {
    HttpRequestStub request = new HttpRequestStub("GET", "/simple_get", "HTTP/1.1", "");
    HttpRequestHandler requestHandler = new HttpRequestHandler();

    Response response = requestHandler.handle(request);

    assertEquals("HTTP/1.1", response.getProtocolVersion());
    assertEquals(200, response.getStatusCode());
    assertEquals("OK", response.getStatusMessage());
    assertEquals("", response.getBody());
  }
}
