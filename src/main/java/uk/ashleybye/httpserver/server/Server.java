package uk.ashleybye.httpserver.server;

public abstract class Server {

  protected final Port port;
  protected ConnectionHandler connectionHandler;

  public Server(Port port) {
    this.port = port;
  }

  public void start() {
    configure();
    while (running()) {
      ConnectionListener listener = new ServerConnectionListener(port, connectionHandler);
      listener.listenForConnections();
      listener.stopListeningForConnections();
    }
  }

  protected abstract void configure();

  private boolean running() {
    return port.isContinuingListening();
  }
}
