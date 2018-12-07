package uk.ashleybye.httpserver.http;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.server.ClosingClientConnectionException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.ConnectionHandler;
import uk.ashleybye.httpserver.server.IncomingClientConnectionException;
import uk.ashleybye.httpserver.server.OutgoingClientConnectionException;
import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.ResponseHandler;

public class HttpConnectionHandler implements ConnectionHandler {

  private final RequestHandler requestHandler;
  private final PrintWriter errorOut;

  public HttpConnectionHandler(RequestHandler requestHandler, PrintWriter errorOut) {
    this.requestHandler = requestHandler;
    this.errorOut = errorOut;
  }

  @Override
  public void handleConnection(Connection connection) {
    try {
      String incomingData = connection.receiveData();
      ResponseHandler responseHandler = requestHandler.buildRequest(incomingData);
      String outgoingData = responseHandler.serializeResponse();
//      System.out.println(outgoingData);
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
