package uk.ashleybye.httpserver;

import static uk.ashleybye.httpserver.http.RequestMethod.GET;
import static uk.ashleybye.httpserver.http.RequestMethod.POST;
import static uk.ashleybye.httpserver.http.RequestMethod.PUT;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import uk.ashleybye.httpserver.http.HttpRequestParser;
import uk.ashleybye.httpserver.http.Router;
import uk.ashleybye.httpserver.http.controller.EchoBodyController;
import uk.ashleybye.httpserver.http.controller.GetWithBodyController;
import uk.ashleybye.httpserver.http.controller.MethodOptionsTwoController;
import uk.ashleybye.httpserver.http.controller.SimpleGetController;
import uk.ashleybye.httpserver.http.router.HttpRouter;
import uk.ashleybye.httpserver.server.HttpPort;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.RequestParser;
import uk.ashleybye.httpserver.server.Server;

public class HttpServer {

  public static void main(String[] args) {
    Port port = new HttpPort(5000);
    RequestParser parser = new HttpRequestParser();
    Router router = configureRouter();
    PrintWriter errorOut = new PrintWriter(System.err, true);
    Executor executor = Executors.newSingleThreadExecutor();
    Server server = new Server(parser, router, errorOut, executor);
    server.start(port);
  }

  private static Router configureRouter() {
    return new HttpRouter()
        .addRoute("/simple_get", new SimpleGetController(GET))
        .addRoute("/get_with_body", new GetWithBodyController())
        .addRoute("/method_options", new GetWithBodyController(GET))
        .addRoute("/method_options2", new MethodOptionsTwoController(GET, PUT, POST))
        .addRoute("/echo_body", new EchoBodyController(POST));
  }
}
