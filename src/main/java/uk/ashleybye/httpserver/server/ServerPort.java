package uk.ashleybye.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPort implements Port {

  private int port;
  private ServerSocket serverSocket;

  public ServerPort(int port) {
    this.port = port;
  }

  public void listen(ConnectionListener connectionListener) {
    try {
      serverSocket = new ServerSocket(port);
      Socket socket = serverSocket.accept();
      connectionListener.handleConnection(new ClientConnection(socket));
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public void close() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      throw new ClosingServerPortException();
    }
  }

  @Override
  public boolean isContinuingListening() {
    return true;
  }
}
