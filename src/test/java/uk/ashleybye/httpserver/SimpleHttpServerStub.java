package uk.ashleybye.httpserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleHttpServerStub extends SimpleHttpServer {

  private Map<String, List<String>> routes = new HashMap<>();

  public SimpleHttpServerStub(String validMethodsAndUris) {
    super(null, null);
    String[] methodsAndUris = validMethodsAndUris.split(", ");
    for (String methodAndUri : methodsAndUris) {
      String method = methodAndUri.split(" ")[0].strip();
      String uri = methodAndUri.split(" ")[1].strip();
      if (routes.containsKey(uri)) {
        routes.get(uri).add(method);
      } else {
        routes.put(uri, Arrays.asList(method));
      }
    }
  }

  @Override
  public boolean hasRoute(String uri) {
    return routes.containsKey(uri);
  }
}
