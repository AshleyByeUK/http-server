package uk.ashleybye.httpserver.http.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.server.Request;

class HttpRequestParserTest {

  @Test
  void testParsesHeadRequest() {
    String message = "HEAD /head HTTP/1.1\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    Assertions.assertEquals(RequestMethod.HEAD, request.getMethod());
    assertEquals("/head", request.getUri());
    Assertions.assertEquals(ProtocolVersion.HTTP_1_1, request.getProtocolVersion());
    assertEquals("", request.getBody());
  }

  @Test
  void testParsesOptionsRequest() {
    String message = "OPTIONS /options HTTP/1.1\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(RequestMethod.OPTIONS, request.getMethod());
    assertEquals("/options", request.getUri());
    assertEquals(ProtocolVersion.HTTP_1_1, request.getProtocolVersion());
    assertEquals("", request.getBody());
  }

  @Test
  void testParsesGetRequest() {
    String message = "GET /get HTTP/1.1\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "\r\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(RequestMethod.GET, request.getMethod());
    assertEquals("/get", request.getUri());
    assertEquals(ProtocolVersion.HTTP_1_1, request.getProtocolVersion());
    assertEquals("", request.getBody());
  }

  @Test
  void testParsesPostRequest() {
    String message = "POST /post HTTP/1.1\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "Content-Length: 9\n"
        + "Content-Type: application/x-www-form-urlencoded\n"
        + "\r\n"
        + "post body";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertEquals(RequestMethod.POST, request.getMethod());
    assertEquals("/post", request.getUri());
    assertEquals(ProtocolVersion.HTTP_1_1, request.getProtocolVersion());
    assertEquals("post body", request.getBody());
  }

  @Test
  void testParsesInvalidMethod() {
    String message = "INVALID /simple_get HTTP/1.1\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "\r\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertTrue(request.hasParseError());
  }

  @Test
  void testParsesUnsupportedProtocolVersion() {
    String message = "GET /simple_get HTTP/0.0\n"
        + "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n"
        + "Accept: */*\n"
        + "User-Agent: Ruby\n"
        + "Connection: close\n"
        + "Host: 0.0.0.0:5000\n"
        + "\r\n";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertTrue(request.hasParseError());
  }

  @Test
  void testParsesMalformedRequest() {
    String message = "GET";
    HttpRequestParser parser = new HttpRequestParser();

    Request request = parser.parse(message);

    assertTrue(request.hasParseError());
  }
}
