package uk.ashleybye.httpserver.http;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.server.ClosingClientConnectionException;
import uk.ashleybye.httpserver.server.Connection;
import uk.ashleybye.httpserver.server.ConnectionHandler;
import uk.ashleybye.httpserver.server.IncomingClientConnectionException;
import uk.ashleybye.httpserver.server.OutgoingClientConnectionException;
import uk.ashleybye.httpserver.server.Request;
import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.RequestParser;
import uk.ashleybye.httpserver.server.Response;
import uk.ashleybye.httpserver.server.ResponseSerializer;

public class HttpConnectionHandler implements ConnectionHandler {

  private final RequestParser parser;
  private final RequestHandler handler;
  private final ResponseSerializer serializer;
  private final PrintWriter errorOut;

  public HttpConnectionHandler(RequestParser parser, RequestHandler handler, ResponseSerializer serializer, PrintWriter errorOut) {
    this.parser = parser;
    this.handler = handler;
    this.serializer = serializer;
    this.errorOut = errorOut;
  }

  @Override
  public void handleConnection(Connection connection) {
    try {
      String incomingData = connection.receiveData();
      Request request = parser.parse(incomingData);
      Response response = handler.handle(request);
      String outgoingData = serializer.serialize(response);
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
