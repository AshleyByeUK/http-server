package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.server.Request;

class HttpRequestParserTest {

  @Test
  void testParsesSimpleGetRequest() {
    String message = "GET /simple_get HTTP/1.1\n\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(RequestMethod.GET, request.getMethod());
    assertEquals("/simple_get", request.getUri());
    assertEquals(ProtocolVersion.HTTP_1_1, request.getProtocolVersion());
    assertEquals("", request.getBody());
  }

  @Test
  void testParsesInvalidMethod() {
    String message = "INVALID /simple_get HTTP/1.1\n\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(RequestMethod.INVALID_METHOD, request.getMethod());
  }

  @Test
  void testParsesUnsupportedProtocolVersion() {
    String message = "GET /simple_get HTTP/0.0\n\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(ProtocolVersion.NOT_SUPPORTED, request.getProtocolVersion());
  }
}
