package uk.ashleybye.httpserver.server;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;

public interface Request {

  Response respond(Controller controller);

  RequestMethod getMethod();

  String getUri();

  ProtocolVersion getProtocolVersion();

  String getBody();
}
