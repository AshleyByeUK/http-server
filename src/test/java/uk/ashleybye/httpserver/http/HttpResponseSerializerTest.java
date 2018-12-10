package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HttpResponseSerializerTest {

  @Test
  void testSerializesSimpleGetRequest() {
    Response response = new HttpResponseStub("HTTP/1.1", 200, "OK", "");
    HttpResponseSerializer serializer = new HttpResponseSerializer();

    String message = serializer.serialize(response);

    assertEquals("HTTP/1.1 200 OK\n", message);
  }
}