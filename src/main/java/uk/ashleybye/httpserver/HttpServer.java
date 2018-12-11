package uk.ashleybye.httpserver;

import java.io.PrintWriter;
import uk.ashleybye.httpserver.http.HttpMiddleware;
import uk.ashleybye.httpserver.http.HttpRequestParser;
import uk.ashleybye.httpserver.http.HttpResponseSerializer;
import uk.ashleybye.httpserver.http.RequestParser;
import uk.ashleybye.httpserver.http.ResponseSerializer;
import uk.ashleybye.httpserver.http.Router;
import uk.ashleybye.httpserver.server.HttpPort;
import uk.ashleybye.httpserver.server.Middleware;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.Server;

public class HttpServer {

  public static void main(String[] args) {
    Port port = new HttpPort(5000);
    PrintWriter errorOut = new PrintWriter(System.err, true);
    Router router = new Router();
    router.addRoute("/simple_get");
    RequestParser requestParser = new HttpRequestParser();
    ResponseSerializer responseSerializer = new HttpResponseSerializer();
    Middleware middleware = new HttpMiddleware(requestParser, router, responseSerializer);
    Server server = new Server(port, middleware, errorOut);
    server.start();
    server.stop();
  }
}
