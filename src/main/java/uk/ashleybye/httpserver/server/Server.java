package uk.ashleybye.httpserver.server;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

  private final Port port;
  private final RequestParser parser;
  private final Router router;
  private final PrintWriter errorOut;
  private boolean running;

  public Server(Port port, RequestParser parser, Router router, PrintWriter errorOut) {
    this.port = port;
    this.parser = parser;
    this.router = router;
    this.errorOut = errorOut;
  }

  public void start() {
    try {
      serverTask().get();
    } catch (Exception e) {
      String exception = e.getCause().toString();
      if (exception.equals(PortUnavailableException.class.getCanonicalName())) {
        throw new PortUnavailableException();
      }
    }
  }

  private Future<Void> serverTask() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    return executorService.submit(() -> {
      listen();
      return null;
    });
  }

  private void listen() {
    running = true;
    while (!port.isClosed()) { // TODO: use running field once proper multiple connection handling implemented.
      port.listen(this);
    }
  }

  public void handleConnection(Connection connection) {
    // TODO: will need to keep track of connections here.
    try {
      Request request = parser.parse(connection.receiveData());
      Response response = router.route(request);
      connection.sendData(response.serialize());
      connection.close();
      port.close(); // TODO: handle multiple connections properly and remove this line.
    } catch (IncomingConnectionException e) {
      errorOut.println("Could not read data from incoming server");
    } catch (OutgoingConnectionException e) {
      errorOut.println("Could not write data to outgoing server");
    } catch (ClosingConnectionException e) {
      errorOut.println("Could not close outgoing server");
    }
  }

  public void stop() {
    port.close(); // TODO: multiple connection handling will need to close all connections here.
    running = false;
  }
}
