package uk.ashleybye.httpserver.http;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.server.ClosingClientConnectionException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.ConnectionHandler;
import uk.ashleybye.httpserver.server.IncomingClientConnectionException;
import uk.ashleybye.httpserver.server.OutgoingClientConnectionException;
import uk.ashleybye.httpserver.server.RequestHandler;

public class HttpConnectionHandler implements ConnectionHandler {

  private final RequestHandler handler;
  private final PrintWriter errorOut;

  public HttpConnectionHandler(RequestHandler handler, PrintWriter errorOut) {
    this.handler = handler;
    this.errorOut = errorOut;
  }

  @Override
  public void handleConnection(Connection connection) {
    try {
      String incomingData = connection.receiveData();
      String outgoingData = handler.handle(incomingData);
      connection.sendData(outgoingData);
      connection.close();
    } catch (IncomingClientConnectionException e) {
      errorOut.println("Could not read data from incoming server");
    } catch (OutgoingClientConnectionException e) {
      errorOut.println("Could not write data to outgoing server");
    } catch (ClosingClientConnectionException e) {
      errorOut.println("Could not close outgoing server");
    }
  }
}
