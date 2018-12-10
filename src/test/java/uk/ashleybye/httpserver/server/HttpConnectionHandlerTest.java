package uk.ashleybye.httpserver.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.http.HttpConnectionHandler;
import uk.ashleybye.httpserver.http.HttpRequestHandler;
import uk.ashleybye.httpserver.http.HttpRequestParserStub;
import uk.ashleybye.httpserver.http.HttpRequestStub;
import uk.ashleybye.httpserver.http.HttpResponseSerializer;
import uk.ashleybye.httpserver.http.HttpResponseSerializerStub;

public class HttpConnectionHandlerTest {

  private RequestParser requestParser;
  private StringWriter stdErr;
  private PrintWriter errorOut;
  private ErrorClientConnectionStub connectionWithError;
  private RequestHandler requestHandler;
  private ResponseSerializer responseSerializer;

  @BeforeEach
  void setUp() {
    requestParser = new HttpRequestParserStub(new HttpRequestStub("GET", "/", "HTTP/1.1", ""));
    requestHandler = new HttpRequestHandlerStub();
    responseSerializer = new HttpResponseSerializerStub();
    stdErr = new StringWriter();
    errorOut = new PrintWriter(stdErr);
    connectionWithError = new ErrorClientConnectionStub();
  }

  @Test
  void testIncomingClientConnectionExceptionIsHandled() {
    connectionWithError.receiveDataShouldThrowIncomingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestParser, requestHandler, responseSerializer, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not read data from incoming server\n", stdErr.toString());
  }

  @Test
  void testOutgoingClientConnectionExceptionIsHandled() {
    connectionWithError.sendDataShouldThrowOutgoingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestParser, requestHandler, responseSerializer, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not write data to outgoing server\n", stdErr.toString());
  }

  @Test
  void testClosingClientConnectionExceptionIsHandled() {
    connectionWithError.closeShouldThrowClosingClientConnectionException();

    ConnectionHandler handler = new HttpConnectionHandler(requestParser, requestHandler, responseSerializer, errorOut);
    handler.handleConnection(connectionWithError);

    assertEquals("Could not close outgoing server\n", stdErr.toString());
  }
}
