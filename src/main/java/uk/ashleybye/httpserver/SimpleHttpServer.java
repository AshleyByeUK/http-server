package uk.ashleybye.httpserver;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.http.HttpServer;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.Server;
import uk.ashleybye.httpserver.server.ServerPort;

public class SimpleHttpServer extends HttpServer {

  public SimpleHttpServer(Port port, PrintWriter errorOut) {
    super(port, errorOut);
  }

  public static void main(String[] args) {
    Port port = new ServerPort(5000);
    Server server = new SimpleHttpServer(port, new PrintWriter(System.err, true));
    server.start();
  }

  @Override
  protected void initialize() {
    addRoute("/simple_get");
  }
}
