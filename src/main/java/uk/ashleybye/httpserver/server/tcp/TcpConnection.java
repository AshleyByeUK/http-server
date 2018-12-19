package uk.ashleybye.httpserver.server.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import uk.ashleybye.httpserver.server.ClosingConnectionException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.IncomingConnectionException;
import uk.ashleybye.httpserver.server.OutgoingConnectionException;

public class TcpConnection implements Connection {

  private final Socket socket;

  public TcpConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public String receiveData() {
    try {
      InputStream inputStream = socket.getInputStream();
      return readAllBytes(inputStream);
    } catch (IOException e) {
      throw new IncomingConnectionException(e);
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
      throw new OutgoingConnectionException(e);
    }
  }

  @Override
  public void close() {
    try {
      socket.close();
    } catch (IOException e) {
      throw new ClosingConnectionException(e);
    }
  }
}
