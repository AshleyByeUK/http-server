package uk.ashleybye.httpserver.http.router;

import static uk.ashleybye.httpserver.http.StatusCode.MOVED_PERMANENTLY;

import uk.ashleybye.httpserver.server.Responder;

class RedirectResponder {

  private RedirectResponder() {
  }

  static Responder getResponder(String uri) {
    return ((request, response) -> {
      response.setProtocolVersion(request.getProtocolVersion());
      response.setStatusCode(MOVED_PERMANENTLY);
      response.addHeader("Location", uri);
    });
  }
}
