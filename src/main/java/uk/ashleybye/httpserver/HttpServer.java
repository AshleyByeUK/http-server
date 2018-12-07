package uk.ashleybye.httpserver;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.http.HttpConnectionHandler;
import uk.ashleybye.httpserver.http.HttpRequestHandler;
import uk.ashleybye.httpserver.server.ConnectionHandler;
import uk.ashleybye.httpserver.server.ConnectionListener;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.ServerConnectionListener;
import uk.ashleybye.httpserver.server.ServerPort;

public class HttpServer {

  private final Port port;
  private final PrintWriter errorOut;

  public HttpServer(Port port, PrintWriter errorOut) {
    this.port = port;
    this.errorOut = errorOut;
  }

  public static void main(String[] args) {
    Port port = new ServerPort(5000);
    HttpServer server = new HttpServer(port, new PrintWriter(System.err, true));
    server.start();
  }

  public void start() {
    RequestHandler requestHandler = new HttpRequestHandler();
    ConnectionHandler handler = new HttpConnectionHandler(requestHandler, errorOut);
    ConnectionListener listener = new ServerConnectionListener(port, handler);
    listener.listenForConnections();
    listener.stopListeningForConnections();
  }
}
