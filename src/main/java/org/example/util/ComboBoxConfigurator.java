package org.example.util;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.stream.IntStream;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ComboBoxConfigurator {

  private static final RuleBasedNumberFormat numberFormat = new RuleBasedNumberFormat(Locale.UK,
      RuleBasedNumberFormat.SPELLOUT);
  private final HashMap<Class<?>, Integer> dictLabs = ParserLaboratories.parserLaboratories();

  public ComboBoxConfigurator() throws ClassNotFoundException {
  }

  public void configureCombobox(ComboBox<String> comboBox, Button button) {
    configureDefaultCombobox(comboBox);

    var linkedList = new LinkedList<String>();
    var numLab = button.getText().split(" ")[1];
    var nameLab = parseNumToNameOfClass(numLab);
    int countMethods = 0;
    for (var key : dictLabs.keySet()) {
      if (key.getName().equals(nameLab)) {
        countMethods = dictLabs.get(key);
      }
    }

    IntStream.range(1, countMethods + 1).forEach(x -> linkedList.add(x + " Задание"));
    comboBox.getItems().addAll(linkedList);
  }

  public String parseNumToNameOfClass(String numberOfQuestion) {
    //Programming.FourthSemLab.SecondLab.Main
    return "Programming.FourthSemLab." + capitalizeFirstLetter(
        numberFormat.format(Integer.parseInt(numberOfQuestion), "%spellout-ordinal")) + "Lab.Main";
  }

  public static String capitalizeFirstLetter(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    return Character.toUpperCase(input.charAt(0)) + input.substring(1);
  }

  private static void configureDefaultCombobox(ComboBox<String> comboBox){
    var selectedValue = comboBox.getValue();
    comboBox.getItems().clear();

    if (selectedValue != null) {

      if (comboBox.getItems().contains(selectedValue))
        comboBox.setValue(selectedValue);

      else
        comboBox.getSelectionModel().selectFirst();

    }
  }
}



