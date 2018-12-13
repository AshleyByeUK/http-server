package uk.ashleybye.httpserver;

import static uk.ashleybye.httpserver.http.RequestMethod.GET;
import static uk.ashleybye.httpserver.http.RequestMethod.POST;
import static uk.ashleybye.httpserver.http.RequestMethod.PUT;

import java.io.PrintWriter;
import java.util.concurrent.Executors;
import uk.ashleybye.httpserver.http.controller.EchoBodyController;
import uk.ashleybye.httpserver.http.controller.GetWithBodyController;
import uk.ashleybye.httpserver.http.controller.MethodOptionsTwoController;
import uk.ashleybye.httpserver.http.controller.RedirectController;
import uk.ashleybye.httpserver.http.controller.SimpleGetController;
import uk.ashleybye.httpserver.http.request.HttpRequestParser;
import uk.ashleybye.httpserver.http.router.HttpRouter;
import uk.ashleybye.httpserver.server.Server;
import uk.ashleybye.httpserver.server.tcp.HttpPort;

public class HttpServer {

  public static void main(String[] args) {
    Server server = new Server(
        new HttpRequestParser(),
        configureRouter(),
        new PrintWriter(System.err, true),
        Executors.newSingleThreadExecutor());
    server.start(new HttpPort(5000));
  }

  private static HttpRouter configureRouter() {
    return new HttpRouter()
        .addRoute("/simple_get", new SimpleGetController(GET))
        .addRoute("/get_with_body", new GetWithBodyController())
        .addRoute("/method_options", new GetWithBodyController(GET))
        .addRoute("/method_options2", new MethodOptionsTwoController(GET, PUT, POST))
        .addRoute("/echo_body", new EchoBodyController(POST))
        .addRoute("/redirect", new RedirectController(GET));
  }
}
