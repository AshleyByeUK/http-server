package uk.ashleybye.httpserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Connection {

  private final Socket socket;

  public ClientConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public String receiveData() {
    try {
      return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    } catch (IOException e) {
      throw new IncomingClientConnectionException();
    }
  }

  @Override
  public void sendData(String data) {
    try {
      new PrintWriter(socket.getOutputStream(), true).println(data);
    } catch (IOException e) {
      throw new OutgoingClientConnectionException();
    }
  }

  @Override
  public void close() {
    try {
      socket.close();
    } catch (IOException e) {
      throw new ClosingClientConnectionException();
    }
  }
}
