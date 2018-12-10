package uk.ashleybye.httpserver.http;

import uk.ashleybye.httpserver.server.Response;
import uk.ashleybye.httpserver.server.ResponseSerializer;

public class HttpResponseSerializerStub implements ResponseSerializer {

  @Override
  public String serialize(Response response) {
    return null;
  }
}
