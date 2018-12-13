package uk.ashleybye.httpserver.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpConnection implements Connection {

  private final Socket socket;

  public HttpConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public String receiveData() {
    try {
      InputStream inputStream = socket.getInputStream();
      return readAllBytes(inputStream);
    } catch (IOException e) {
      throw new IncomingConnectionException();
    }
  }

  private String readAllBytes(InputStream inputStream) throws IOException {
    StringBuilder incomingData = new StringBuilder();
    do {
      incomingData.append(readNextByte(inputStream));
    } while (inputStream.available() > 0);
    return incomingData.toString();
  }

  private char readNextByte(InputStream inputStream) throws IOException {
    return (char) inputStream.read();
  }

  @Override
  public void sendData(String data) {
    try {
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
      printWriter.print(data);
      printWriter.close();
    } catch (IOException e) {
      throw new OutgoingConnectionException();
    }
  }

  @Override
  public void close() {
    try {
      socket.close();
    } catch (IOException e) {
      throw new ClosingConnectionException();
    }
  }
}
