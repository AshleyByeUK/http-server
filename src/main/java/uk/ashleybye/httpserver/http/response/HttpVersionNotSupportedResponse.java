package uk.ashleybye.httpserver.http.response;

import uk.ashleybye.httpserver.http.ProtocolVersion;
import uk.ashleybye.httpserver.http.StatusCode;

public class HttpVersionNotSupportedResponse extends HttpResponse {

  public HttpVersionNotSupportedResponse() {
    setProtocolVersion(ProtocolVersion.HTTP_1_1);
    setStatusCode(StatusCode.HTTP_VERSION_NOT_SUPPORTED);
    setBody("");
  }
}
