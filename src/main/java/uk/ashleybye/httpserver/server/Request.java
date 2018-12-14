package uk.ashleybye.httpserver.server;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;

public interface Request {

  void respond(Responder handler, Response response);

  boolean hasParseError();

  RequestMethod getMethod();

  String getUri();

  ProtocolVersion getProtocolVersion();

  String getBody();
}
