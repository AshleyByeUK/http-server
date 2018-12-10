package uk.ashleybye.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.SimpleHttpServerStub;
import uk.ashleybye.httpserver.server.ConnectionHandler;
import uk.ashleybye.httpserver.server.ErrorClientConnectionStub;
import uk.ashleybye.httpserver.server.RequestHandler;

public class HttpConnectionHandlerTest {

  private StringWriter stdErr;
  private PrintWriter errorOut;
  private ErrorClientConnectionStub connectionWithError;
  private RequestHandler requestHandler;

  @BeforeEach
  void setUp() {
    RequestParser requestParser = new HttpRequestParser();
    ResponseSerializer responseSerializer = new HttpResponseSerializer();
    HttpServer server = new SimpleHttpServerStub("GET /simple_get");
    requestHandler = new HttpRequestHandler(requestParser, responseSerializer, server);
    stdErr = new StringWriter();
    errorOut = new PrintWriter(stdErr);
    connectionWithError = new ErrorClientConnectionStub();
  }

  @Test
  void testIncomingClientConnectionExceptionIsHandled() {
    connectionWithError.receiveDataShouldThrowIncomingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestHandler, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not read data from incoming server\n", stdErr.toString());
  }

  @Test
  void testOutgoingClientConnectionExceptionIsHandled() {
    connectionWithError.sendDataShouldThrowOutgoingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestHandler, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not write data to outgoing server\n", stdErr.toString());
  }

  @Test
  void testClosingClientConnectionExceptionIsHandled() {
    connectionWithError.closeShouldThrowClosingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestHandler, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not close outgoing server\n", stdErr.toString());
  }
}
