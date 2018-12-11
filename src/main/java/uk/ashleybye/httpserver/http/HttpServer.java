package uk.ashleybye.httpserver.http;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import uk.ashleybye.httpserver.server.Port;
import uk.ashleybye.httpserver.server.RequestHandler;
import uk.ashleybye.httpserver.server.Server;

public abstract class HttpServer extends Server {

  private final PrintWriter errorOut;
  private List<String> routes = new ArrayList<>();

  public HttpServer(Port port, PrintWriter errorOut) {
    super(port);
    this.errorOut = errorOut;
  }

  @Override
  protected final void configure() {
    initialize();
    RequestParser requestParser = new HttpRequestParser();
    ResponseSerializer responseSerializer = new HttpResponseSerializer();
    RequestHandler requestHandler = new HttpRequestHandler(requestParser, responseSerializer, this);
    connectionHandler = new HttpConnectionHandler(requestHandler, errorOut);
  }

  protected abstract void initialize();

  protected void addRoute(String uri) {
    routes.add(uri);
  }

  public boolean hasRoute(String uri) {
    return routes.contains(uri);
  }
}
