package uk.ashleybye.httpserver.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ServerConnectionListenerTest {

  @Test
  void testSendsConnectionToHandler() {
    Port port = new PortSpy(new ConnectionSpy("incoming data"));
    ConnectionHandlerSpy handler = new ConnectionHandlerSpy();

    ConnectionListener listener = new ServerConnectionListener(port, handler);
    listener.listenForConnections();

    Connection handledConnection = handler.getHandledConnection();
    assertEquals("incoming data", handledConnection.receiveData());
  }
}
