package uk.ashleybye.httpserver.server;

public interface Controller {

  void head(Request request, Response response);

  void options(Request request, Response response);

  void get(Request request, Response response);

  void post(Request request, Response response);
}
