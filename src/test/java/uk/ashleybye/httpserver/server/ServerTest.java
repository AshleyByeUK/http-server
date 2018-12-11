package uk.ashleybye.httpserver.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.HttpMiddleware;
import uk.ashleybye.httpserver.http.HttpRequestParser;
import uk.ashleybye.httpserver.http.HttpResponseSerializer;
import uk.ashleybye.httpserver.http.RequestParser;
import uk.ashleybye.httpserver.http.ResponseSerializer;
import uk.ashleybye.httpserver.http.Router;

public class ServerTest {

  private PortSpy port;
  private Middleware middleware;
  private StringWriter stdErr = new StringWriter();
  private PrintWriter errorOut = new PrintWriter(stdErr);
  private ErrorClientConnectionStub connectionWithError = new ErrorClientConnectionStub();

  @BeforeEach
  void setUp() {
    RequestParser requestParser = new HttpRequestParser();
    ResponseSerializer responseSerializer = new HttpResponseSerializer();
    Router router = new Router().addRoute("/simple_get");
    middleware = new HttpMiddleware(requestParser, router, responseSerializer);
  }

  @Test
  void testIncomingConnectionExceptionIsHandled() {
    connectionWithError.receiveDataShouldThrowIncomingConnectionException();
    port = new PortSpy(connectionWithError);

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals("Could not read data from incoming server\n", stdErr.toString());

    server.stop();
  }

  @Test
  void testOutgoingConnectionExceptionIsHandled() {
    connectionWithError.sendDataShouldThrowOutgoingConnectionException();
    port = new PortSpy(connectionWithError);

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals("Could not write data to outgoing server\n", stdErr.toString());

    server.stop();
  }

  @Test
  void testClosingConnectionExceptionIsHandled() {
    connectionWithError.closeShouldThrowClosingConnectionException();
    port = new PortSpy(connectionWithError);

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals("Could not close outgoing server\n", stdErr.toString());

    server.stop();
  }

  @Test
  void testUnavailablePortThrowsPortUnavailableException() {
    UnavailablePortStub port = new UnavailablePortStub();
    PrintWriter errorOut = new PrintWriter(new StringWriter());

    Server server = new Server(port, middleware, errorOut);

    assertThrows(PortUnavailableException.class, () -> server.start());

    server.stop();
  }

  @Test
  void testCanStartAndStopServer() {
    ConnectionSpy connection = new ConnectionSpy("GET /simple_get HTTP/1.1\n\n");
    port = new PortSpy(connection);

    Server server = new Server(port, middleware, errorOut);
    server.start();
    server.stop();

    assertTrue(port.isClosed());
    assertTrue(connection.isClosed());
  }

  @Test
  void testErrorClosingPortThrowsClosingServerPortException() {
    ErrorClosingPortStub port = new ErrorClosingPortStub();

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertThrows(ClosingPortException.class, () -> server.stop());
  }

  @Test
  void testGetRequestWithEmptyBody() {
    ConnectionSpy connection = new ConnectionSpy("GET /simple_get HTTP/1.1\n\n");
    port = new PortSpy(connection);

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals("HTTP/1.1 200 OK\n", connection.getSentData());

    server.stop();
  }

  @Test
  void testResourceNotFound() {
    ConnectionSpy connection = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\n");
    port = new PortSpy(connection);

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());

    server.stop();
  }

  @Test
  void testServesMultipleRequests() {
    ConnectionSpy connectionOne = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\n");
    ConnectionSpy connectionTwo = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\n");
    port = new PortSpy(connectionOne, connectionTwo);
    port.resetNumberOfTimesListenCalled(); // TODO: Remove this line and method once proper multiple connection handling.

    Server server = new Server(port, middleware, errorOut);
    server.start();

    assertEquals(2, port.getNumberOfTimesListenCalled());
    assertTrue(connectionOne.isClosed());
    assertTrue(connectionTwo.isClosed());

    server.stop();
  }
}
