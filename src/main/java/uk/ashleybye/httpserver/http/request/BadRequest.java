package uk.ashleybye.httpserver.http.request;

import static uk.ashleybye.httpserver.http.StatusCode.BAD_REQUEST;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.server.Responder;
import uk.ashleybye.httpserver.server.Response;

class BadRequest extends HttpRequest {

  @Override
  public void respond(Responder controller, Response response) {
    response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
    response.setStatusCode(BAD_REQUEST);
  }

  @Override
  public boolean hasParseError() {
    return true;
  }
}
