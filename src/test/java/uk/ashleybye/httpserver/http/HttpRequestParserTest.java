package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HttpRequestParserTest {

  @Test
  void testParsesSimpleGetRequest() {
    String message = "GET /simple_get HTTP/1.1\n\n";
    HttpRequestParser parser = new HttpRequestParser();

    HttpRequest request = parser.parse(message);

    assertEquals("GET", request.getMethod());
    assertEquals("/simple_get", request.getUri());
    assertEquals("HTTP/1.1", request.getProtocolVersion());
    assertEquals("", request.getBody());
  }
}
