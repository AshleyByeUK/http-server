package uk.ashleybye.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpPort implements Port {

  private int port;
  private ServerSocket serverSocket;

  public HttpPort(int port) {
    this.port = port;
  }

  public void listen(Server server) {
    try {
      serverSocket = new ServerSocket(port);
      Socket socket = serverSocket.accept();
      server.handleConnection(new HttpConnection(socket));
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public void close() { // TODO: Set closed to true once proper multiple client handling implemented.
    try {
      serverSocket.close();
    } catch (IOException e) {
      throw new ClosingPortException();
    }
  }

  @Override
  public boolean isClosed() {
    return false;
  }
}
