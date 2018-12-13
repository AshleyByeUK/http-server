package uk.ashleybye.httpserver.http.response;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.StatusCode;

public class NotImplementedResponse extends HttpResponse {

  public NotImplementedResponse() {
    setProtocolVersion(ProtocolVersion.HTTP_1_1);
    setStatusCode(StatusCode.NOT_IMPLEMENTED);
    setBody("");
  }
}
