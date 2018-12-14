package uk.ashleybye.httpserver.http.router;

import static uk.ashleybye.httpserver.http.ProtocolVersion.HTTP_1_1;
import static uk.ashleybye.httpserver.http.StatusCode.OK;

import uk.ashleybye.httpserver.server.Responder;

class HeadResponder {

  private HeadResponder() {
  }

  static Responder getResponder() {
    return ((request, response) -> {
      response.setProtocolVersion(HTTP_1_1);
      response.setStatusCode(OK);
    });
  }
}
