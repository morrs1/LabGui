package org.example.util;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class ParserConditions {

  private static final JSONParser parser = new JSONParser();
  private final JSONObject jsonObject;

  public ParserConditions() {
    this.jsonObject = ParserConditions.parseConditions();
  }

  private static JSONObject parseConditions() {
    try (var reader = new FileReader("src/main/resources/Condition.json")) {

      return (JSONObject) parser.parse(reader);

    } catch (IOException | ParseException e) {
      return null;
    }
  }

  public String get(String numLab, String numTask) {
    var lab = (JSONObject) jsonObject.get(numLab);
    return (String) lab.get(numTask);
  }
}
