package uk.ashleybye.httpserver.server;

import java.io.PrintWriter;
import java.util.concurrent.Executor;
import uk.ashleybye.httpserver.http.Router;

public class Server {

  private final RequestParser parser;
  private final Router router;
  private final PrintWriter errorOut;
  private final Executor executor;
  private Port port;
  private boolean running;

  public Server(RequestParser parser, Router router, PrintWriter errorOut, Executor executor) {
    this.parser = parser;
    this.router = router;
    this.errorOut = errorOut;
    this.executor = executor;
  }

  public void start(Port port) {
    this.port = port;
    this.port.listen();
    running = true;
    while (running) {
      executor.execute(new ConnectionRunnable(this.port.acceptConnection()));
    }
  }

  public void stop() {
    port.close();
    running = false;
  }

  private class ConnectionRunnable implements Runnable {

    private final Connection connection;

    ConnectionRunnable(Connection connection) {
      this.connection = connection;
    }

    @Override
    public void run() {
      try {
        Request request = parser.parse(connection.receiveData());
        Response response = router.route(request);
        connection.sendData(response.serialize());
        connection.close();
      } catch (IncomingConnectionException e) {
        errorOut.println("Could not read data from incoming connection");
      } catch (OutgoingConnectionException e) {
        errorOut.println("Could not write data to outgoing connection");
      } catch (ClosingConnectionException e) {
        errorOut.println("Could not close connection");
      }
    }
  }
}
