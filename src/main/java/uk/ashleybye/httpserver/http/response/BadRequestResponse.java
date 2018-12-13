package uk.ashleybye.httpserver.http.response;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.StatusCode;

public class BadRequestResponse extends HttpResponse {

  public BadRequestResponse() {
    setProtocolVersion(ProtocolVersion.HTTP_1_1);
    setStatusCode(StatusCode.BAD_REQUEST);
    setBody("");
  }
}
