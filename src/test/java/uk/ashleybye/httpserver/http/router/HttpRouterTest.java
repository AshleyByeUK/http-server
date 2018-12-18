package uk.ashleybye.httpserver.http.router;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.ashleybye.httpserver.http.ProtocolVersion.HTTP_1_1;
import static uk.ashleybye.httpserver.http.RequestMethod.GET;
import static uk.ashleybye.httpserver.http.RequestMethod.HEAD;
import static uk.ashleybye.httpserver.http.RequestMethod.OPTIONS;
import static uk.ashleybye.httpserver.http.RequestMethod.POST;
import static uk.ashleybye.httpserver.http.RequestMethod.PUT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.request.HttpRequest;
import uk.ashleybye.httpserver.server.Response;

class HttpRouterTest {

  private HttpRouter router;

  @BeforeEach
  void setUp() {
    router = new HttpRouter()
        .addRoute("/get").useCustomResponder(GET, (request, response) -> {
        })
        .addRoute("/get_body").useCustomResponder(GET, (request, response) -> response.setBody("body"))
        .addRoute("/post").useCustomResponder(POST, (request, response) -> response.setBody(request.getBody()))
        .addRoute("/get_post_put").useCustomResponder(GET, (request, response) -> {
        })
        .addRoute("/get_post_put").useCustomResponder(POST, (request, response) -> {
        })
        .addRoute("/get_post_put").useCustomResponder(PUT, (request, response) -> {
        })
        .addRoute("/redirect").useRedirectResponder(GET, "http://127.0.0.1:5000/get");
  }

  @Test
  void testOptionsRequestForInvalidRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(OPTIONS);
    request.setUri("/invalid");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 404 Not Found\n", response.serialize());
  }

  @Test
  void testOptionsRequestForValidRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(OPTIONS);
    request.setUri("/get");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,OPTIONS\n", response.serialize());
  }

  @Test
  void testOptionsRequestForValidRouteWithMultipleMethodsIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(OPTIONS);
    request.setUri("/get_post_put");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,OPTIONS,POST,PUT\n", response.serialize());
  }

  @Test
  void testHeadRequestForInvalidRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(HEAD);
    request.setUri("/invalid");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 404 Not Found\n", response.serialize());
  }

  @Test
  void testHeadRequestForValidRouteWithNoBodyIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(HEAD);
    request.setUri("/get");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\n", response.serialize());
  }

  @Test
  void testHeadRequestForValidRouteWithBodyIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(HEAD);
    request.setUri("/get_body");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\n", response.serialize());
  }

  @Test
  void testGetRequestForValidRouteWithNoBodyIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(GET);
    request.setUri("/get");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\n", response.serialize());
  }

  @Test
  void testGetRequestForValidRouteWithBodyIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(GET);
    request.setUri("/get_body");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\n\r\nbody", response.serialize());
  }

  @Test
  void testPostRequestForValidRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(POST);
    request.setUri("/post");
    request.setProtocolVersion(HTTP_1_1);
    request.setBody("body");

    Response response = router.route(request);

    assertEquals("HTTP/1.1 200 OK\n\r\nbody", response.serialize());
  }

  @Test
  void testPostRequestForInvalidRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(POST);
    request.setUri("/get");
    request.setProtocolVersion(HTTP_1_1);
    request.setBody("body");

    Response response = router.route(request);

    assertEquals("HTTP/1.1 405 Method Not Allowed\nAllow: GET,HEAD,OPTIONS\n", response.serialize());
  }

  @Test
  void testRedirectedRouteIsCorrectlyHandled() {
    HttpRequest request = new HttpRequest();
    request.setMethod(GET);
    request.setUri("/redirect");
    request.setProtocolVersion(HTTP_1_1);

    Response response = router.route(request);

    assertEquals("HTTP/1.1 301 Moved Permanently\nLocation: http://127.0.0.1:5000/get\n", response.serialize());
  }
}
