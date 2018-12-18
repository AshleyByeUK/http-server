package uk.ashleybye.httpserver.http.request;

import static uk.ashleybye.httpserver.http.StatusCode.NOT_IMPLEMENTED;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.server.Responder;
import uk.ashleybye.httpserver.server.Response;

class NotImplementedRequest extends HttpRequest {

  @Override
  public void respond(Responder controller, Response response) {
    response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
    response.setStatusCode(NOT_IMPLEMENTED);
  }

  @Override
  public boolean hasParseError() {
    return true;
  }
}
