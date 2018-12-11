package uk.ashleybye.httpserver.server;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;

public interface Request {

  RequestMethod getMethod();

  String getUri();

  ProtocolVersion getProtocolVersion();

  String getBody();
}
