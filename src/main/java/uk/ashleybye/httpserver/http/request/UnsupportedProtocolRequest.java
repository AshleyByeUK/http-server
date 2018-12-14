package uk.ashleybye.httpserver.http.request;

import static uk.ashleybye.httpserver.http.StatusCode.HTTP_VERSION_NOT_SUPPORTED;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.server.Responder;
import uk.ashleybye.httpserver.server.Response;

public class UnsupportedProtocolRequest extends HttpRequest {

  @Override
  public void respond(Responder controller, Response response) {
    response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
    response.setStatusCode(HTTP_VERSION_NOT_SUPPORTED);
  }

  @Override
  public boolean hasParseError() {
    return true;
  }
}
