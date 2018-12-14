package uk.ashleybye.httpserver.server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import uk.ashleybye.httpserver.server.ClosingPortException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.PortUnavailableException;

public class TcpPort implements Port {

  private int port;
  private ServerSocket serverSocket;

  public TcpPort(int port) {
    this.port = port;
  }

  public void listen() {
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public Connection acceptConnection() {
    try {
      Socket socket = serverSocket.accept();
      return new TcpConnection(socket);
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public void close() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      throw new ClosingPortException();
    }
  }
}
