package uk.ashleybye.httpserver.http.router;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import uk.ashleybye.httpserver.http.RequestMethod;
import uk.ashleybye.httpserver.server.Responder;

class OptionsResponder {

  private OptionsResponder() {
  }

  static Responder getResponder(Set<RequestMethod> allowedMethods) {
    return ((request, response) -> response.addHeader("Allow", stringifyMethods(allowedMethods)));
  }

  private static String stringifyMethods(Set<RequestMethod> methods) {
    List<RequestMethod> list = new ArrayList<>(methods);
    list.sort(Comparator.naturalOrder());
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      string
          .append(currentItemAsString(i, list))
          .append(possibleTrailingComma(i, list));
    }
    return string.toString();
  }

  private static String currentItemAsString(int currentItemNumber, List<RequestMethod> list) {
    return list.get(currentItemNumber).toString();
  }

  private static String possibleTrailingComma(int currentItemNumber, List<RequestMethod> list) {
    return (currentItemNumber < list.size() - 1) ? "," : "";
  }
}
