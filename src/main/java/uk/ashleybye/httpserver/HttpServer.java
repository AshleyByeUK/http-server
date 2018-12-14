package uk.ashleybye.httpserver;

import static uk.ashleybye.httpserver.http.RequestMethod.GET;
import static uk.ashleybye.httpserver.http.RequestMethod.POST;
import static uk.ashleybye.httpserver.http.RequestMethod.PUT;

import java.io.PrintWriter;
import java.util.concurrent.Executors;
import uk.ashleybye.httpserver.http.request.HttpRequestParser;
import uk.ashleybye.httpserver.http.router.HttpRouter;
import uk.ashleybye.httpserver.server.Server;
import uk.ashleybye.httpserver.server.tcp.TcpPort;

class HttpServer {

  public static void main(String[] args) {
    initialiseServer()
        .start(new TcpPort(5000));
  }

  private static Server initialiseServer() {
    return new Server(
        new HttpRequestParser(),
        configuredRouter(),
        new PrintWriter(System.err, true),
        Executors.newSingleThreadExecutor());
  }

  private static HttpRouter configuredRouter() {
    return new HttpRouter()
        .addRoute("/simple_get").useCustomResponder(GET, (request, response) -> {})
        .addRoute("/get_with_body").useDefaultResponder()
        .addRoute("/method_options").useCustomResponder(GET, (request, response) -> {})
        .addRoute("/method_options2").useCustomResponder(GET, (request, response) -> {})
        .addRoute("/method_options2").useCustomResponder(PUT, (request, response) -> {})
        .addRoute("/method_options2").useCustomResponder(POST, (request, response) -> {})
        .addRoute("/echo_body").useCustomResponder(POST, (request, response) -> response.setBody(request.getBody()))
        .addRoute("/redirect").useRedirectResponder(GET, "http://127.0.0.1:5000/simple_get");
  }
}
