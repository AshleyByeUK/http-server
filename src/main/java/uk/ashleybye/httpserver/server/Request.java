package uk.ashleybye.httpserver.server;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.http.controller.Controller;

public interface Request {

  Response respond(Controller controller);

  RequestMethod getMethod();

  void setMethod(RequestMethod method);

  String getUri();

  void setUri(String uri);

  ProtocolVersion getProtocolVersion();

  void setProtocolVersion(ProtocolVersion version);

  String getBody();

  void setBody(String body);
}
