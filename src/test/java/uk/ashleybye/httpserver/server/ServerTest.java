package uk.ashleybye.httpserver.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.httpserver.http.RequestMethod.GET;
import static uk.ashleybye.httpserver.http.RequestMethod.POST;
import static uk.ashleybye.httpserver.http.RequestMethod.PUT;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Executor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.controller.EchoBodyController;
import uk.ashleybye.httpserver.http.controller.GetWithBodyController;
import uk.ashleybye.httpserver.http.controller.MethodOptionsController;
import uk.ashleybye.httpserver.http.controller.MethodOptionsTwoController;
import uk.ashleybye.httpserver.http.controller.RedirectController;
import uk.ashleybye.httpserver.http.controller.SimpleGetController;
import uk.ashleybye.httpserver.http.request.HttpRequestParser;
import uk.ashleybye.httpserver.http.router.HttpRouter;
import uk.ashleybye.httpserver.server.tcp.ConnectionSpy;
import uk.ashleybye.httpserver.server.tcp.ErrorClientConnectionStub;
import uk.ashleybye.httpserver.server.tcp.ErrorClosingPortStub;
import uk.ashleybye.httpserver.server.tcp.PortSpy;
import uk.ashleybye.httpserver.server.tcp.UnavailablePortStub;

public class ServerTest {

  private PortSpy port;
  private StringWriter stdErr = new StringWriter();
  private ErrorClientConnectionStub connectionWithError = new ErrorClientConnectionStub();
  private Server server;

  @BeforeEach
  void setUp() {
    RequestParser parser = new HttpRequestParser();
    Router router = new HttpRouter()
        .addRoute("/simple_get", new SimpleGetController(GET))
        .addRoute("/get_with_body", new GetWithBodyController())
        .addRoute("/method_options", new MethodOptionsController(GET))
        .addRoute("/method_options2", new MethodOptionsTwoController(GET, PUT, POST))
        .addRoute("/echo_body", new EchoBodyController(POST))
        .addRoute("/redirect", new RedirectController(GET));
    PrintWriter errorOut = new PrintWriter(stdErr);
    Executor executor = Runnable::run;
    server = new Server(parser, router, errorOut, executor);
  }

  @Test
  void testIncomingConnectionExceptionIsHandled() {
    connectionWithError.receiveDataShouldThrowIncomingConnectionException();
    port = new PortSpy(server, connectionWithError);

    server.start(port);

    assertEquals("Could not read data from incoming connection\n", stdErr.toString());
  }

  @Test
  void testOutgoingConnectionExceptionIsHandled() {
    port = new PortSpy(server, connectionWithError);
    connectionWithError.sendDataShouldThrowOutgoingConnectionException();

    server.start(port);

    assertEquals("Could not write data to outgoing connection\n", stdErr.toString());
  }

  @Test
  void testClosingConnectionExceptionIsHandled() {
    port = new PortSpy(server, connectionWithError);
    connectionWithError.closeShouldThrowClosingConnectionException();

    server.start(port);

    assertEquals("Could not close connection\n", stdErr.toString());
  }

  @Test
  void testUnavailablePortThrowsPortUnavailableException() {
    UnavailablePortStub port = new UnavailablePortStub();

    assertThrows(PortUnavailableException.class, () -> server.start(port));
  }

  @Test
  void testCanStartAndStopServer() {
    ConnectionSpy connection = new ConnectionSpy("GET /simple_get HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);
    server.stop();

    assertTrue(port.isClosed());
    assertTrue(connection.isClosed());
  }

  @Test
  void testErrorClosingPortThrowsClosingServerPortException() {
    ErrorClosingPortStub port = new ErrorClosingPortStub(server);

    assertThrows(ClosingPortException.class, () -> server.start(port));
  }

  @Test
  void testGetRequestWithEmptyBodyReturnsCorrectHeadersAndNoBody() {
    ConnectionSpy connection = new ConnectionSpy("GET /simple_get HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 200 OK\n", connection.getSentData());
  }

  @Test
  void testHeadRequestForResourceWithBodyReturnsCorrectHeadersAndNoBody() {
    ConnectionSpy connection = new ConnectionSpy("HEAD /get_with_body HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 200 OK\n", connection.getSentData());
  }

  @Test
  void testOptionsRequestForResourceWithOnlyGetReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("OPTIONS /method_options HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,OPTIONS\n", connection.getSentData());
  }

  @Test
  void testOptionsRequestForResourceWithMultipleMethodsReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("OPTIONS /method_options2 HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 200 OK\nAllow: GET,PUT,POST,HEAD,OPTIONS\n", connection.getSentData());
  }

  @Test
  void testHeadResourceNotFoundReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("HEAD /not_found_resource HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());
  }

  @Test
  void testOptionsResourceNotFoundReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("OPTIONS /not_found_resource HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());
  }

  @Test
  void testGetResourceNotFoundReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());
  }

  @Test
  void testPostResourceNotFoundReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("POST /not_found_resource HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());
  }

  @Test
  void testGetNotAllowedMethodReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("GET /get_with_body HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 405 Method Not Allowed\nAllow: HEAD,OPTIONS\n", connection.getSentData());
  }

  @Test
  void testPostNotAllowedMethodReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("POST /get_with_body HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 405 Method Not Allowed\nAllow: HEAD,OPTIONS\n", connection.getSentData());
  }

  @Test
  void testPostMethodReturnsCorrectHeadersAndBody() {
    ConnectionSpy connection = new ConnectionSpy("POST /echo_body HTTP/1.1\n\r\nsome body");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 200 OK\n\r\nsome body", connection.getSentData());
  }

  @Test
  void testRedirectReturnsCorrectHeaders() {
    ConnectionSpy connection = new ConnectionSpy("GET /redirect HTTP/1.1\n\r\n");
    port = new PortSpy(server, connection);

    server.start(port);

    assertEquals("HTTP/1.1 301 Moved Permanently\nLocation: http://127.0.0.1:5000/simple_get\n",
        connection.getSentData());
  }

  @Test
  void testServesMultipleRequests() {
    ConnectionSpy connectionOne = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\r\n");
    ConnectionSpy connectionTwo = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\r\n");
    port = new PortSpy(server, connectionOne, connectionTwo);

    server.start(port);

    assertEquals(2, port.getNumberOfTimesAcceptConnectionCalled());
    assertTrue(connectionOne.isClosed());
    assertTrue(connectionTwo.isClosed());
  }
}
