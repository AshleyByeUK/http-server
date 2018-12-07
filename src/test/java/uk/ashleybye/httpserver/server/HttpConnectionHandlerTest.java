package uk.ashleybye.httpserver.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.HttpConnectionHandler;
import uk.ashleybye.httpserver.http.HttpRequestHandlerStub;
import uk.ashleybye.httpserver.http.HttpResponseHandlerStub;

public class HttpConnectionHandlerTest {

  private RequestHandler requestHandler;
  private StringWriter stdErr;
  private PrintWriter errorOut;
  private ErrorClientConnectionStub connectionWithError;

  @BeforeEach
  void setUp() {
    requestHandler = new HttpRequestHandlerStub(new HttpResponseHandlerStub());
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
