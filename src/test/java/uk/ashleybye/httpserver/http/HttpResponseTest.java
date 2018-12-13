package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.response.HttpResponse;
import uk.ashleybye.httpserver.server.Response;

class HttpResponseTest {

  @Test
  void testSerializesSimpleGetRequest() {
    Response response = new HttpResponse();
    response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
    response.setStatusCode(StatusCode.OK);
    response.setBody("");

    String message = response.serialize();

    assertEquals("HTTP/1.1 200 OK\n", message);
  }
}
