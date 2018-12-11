package uk.ashleybye.httpserver;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.http.HttpRequestParser;
import uk.ashleybye.httpserver.http.HttpRouter;
import uk.ashleybye.httpserver.server.HttpPort;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.RequestParser;
import uk.ashleybye.httpserver.server.Router;
import uk.ashleybye.httpserver.server.Server;

public class HttpServer {

  public static void main(String[] args) {
    Port port = new HttpPort(5000);
    RequestParser parser = new HttpRequestParser();
    Router router = configureRouter();
    PrintWriter errorOut = new PrintWriter(System.err, true);
    Server server = new Server(port, parser, router, errorOut);
    server.start();
    server.stop();
  }

  private static Router configureRouter() {
    return new HttpRouter()
        .addRoute("/simple_get");
  }
}
