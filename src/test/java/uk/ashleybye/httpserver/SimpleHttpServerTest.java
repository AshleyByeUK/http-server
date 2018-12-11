package uk.ashleybye.httpserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import uk.ashleybye.httpserver.server.ClosingServerPortException;
import uk.ashleybye.httpserver.server.ConnectionSpy;
import uk.ashleybye.httpserver.server.ErrorClosingPortStub;
import uk.ashleybye.httpserver.server.MultipleConnectionsSpy;
import uk.ashleybye.httpserver.server.PortSpy;
import uk.ashleybye.httpserver.server.PortUnavailableException;
import uk.ashleybye.httpserver.server.Server;
import uk.ashleybye.httpserver.server.UnavailablePortStub;

class SimpleHttpServerTest {

  @Test
  void testUnavailablePortThrowsPortUnavailableException() {
    UnavailablePortStub port = new UnavailablePortStub();
    PrintWriter errorOut = new PrintWriter(new StringWriter());

    Server server = new SimpleHttpServer(port, errorOut);

    assertThrows(PortUnavailableException.class, () -> server.start());
  }

  @Test
  void testErrorClosingPortThrowsClosingServerPortException() {
    ErrorClosingPortStub port = new ErrorClosingPortStub();
    PrintWriter errorOut = new PrintWriter(new StringWriter());

    Server server = new SimpleHttpServer(port, errorOut);

    assertThrows(ClosingServerPortException.class, () -> server.start());
  }

  @Test
  void testGetRequestWithEmptyBody() {
    ConnectionSpy connection = new ConnectionSpy("GET /simple_get HTTP/1.1\n\n");
    PortSpy port = new PortSpy(connection);
    StringWriter stdErr = new StringWriter();
    PrintWriter errorOut = new PrintWriter(stdErr);

    Server server = new SimpleHttpServer(port, errorOut);
    server.start();

    assertEquals("HTTP/1.1 200 OK\n", connection.getSentData());
    assertFalse(port.isListening());
  }

  @Test
  void testResourceNotFound() {
    ConnectionSpy connection = new ConnectionSpy("GET /not_found_resource HTTP/1.1\n\n");
    PortSpy port = new PortSpy(connection);
    StringWriter stdErr = new StringWriter();
    PrintWriter errorOut = new PrintWriter(stdErr);

    Server server = new SimpleHttpServer(port, errorOut);
    server.start();

    assertEquals("HTTP/1.1 404 Not Found\n", connection.getSentData());
    assertFalse(port.isListening());
  }

  @Test
  void testServesMultipleRequests() {
    MultipleConnectionsSpy connection = new MultipleConnectionsSpy("GET /simple_get HTTP/1.1\n\n");
    PortSpy port = new PortSpy(connection);
    port.setNumberOfConnectionsToSimulate(2);
    StringWriter stdErr = new StringWriter();
    PrintWriter errorOut = new PrintWriter(stdErr);

    Server server = new SimpleHttpServer(port, errorOut);
    server.start();

    assertEquals(2, connection.getNumberOfTimesReceivedData());
  }
}
