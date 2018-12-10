package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.server.Response;

class HttpResponseSerializerTest {

  @Test
  void testSerializesSimpleGetRequest() {
    Response response = new HttpResponseStub("HTTP/1.1", 200, "OK", "");
    HttpResponseSerializer serializer = new HttpResponseSerializer();

    String message = serializer.serialize(response);

    assertEquals("HTTP/1.1 200 OK\n", message);
  }
}
