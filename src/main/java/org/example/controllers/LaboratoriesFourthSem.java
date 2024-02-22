package org.example.controllers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.util.ButtonConfigurator;
import org.example.util.ComboBoxConfigurator;
import org.example.util.ConsoleReader;
import org.example.util.ParserConditions;
import org.example.util.ParserLaboratories;


public class LaboratoriesFourthSem implements Initializable {
private String buttonText;
  @FXML
  private Button btnExit, btnBackToMenu;
  @FXML
  private TextArea textAreaCondition, textAreaOutPut;
  private final ControllerOfScene controllerOfScene = new ControllerOfScene();
  private final ButtonConfigurator buttonConfigurator = new ButtonConfigurator();
  private final ParserConditions data = new ParserConditions();
  private final HashMap<Class<?>, Integer> dictLabs = ParserLaboratories.parserLaboratories();
  private final ComboBoxConfigurator comboBoxConfigurator = new ComboBoxConfigurator();
  @FXML
  private Button secondButton, thirdButton, fourthButton, fifthButton, sixthButton, seventhButton, startButton, clearButton;
  @FXML
  private ComboBox<String> comboBox;
  @FXML
  private TextField textField;



  public LaboratoriesFourthSem() throws ClassNotFoundException {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    laboratoriesButtonsEvent();
    buttonEvent();
    comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {

      if (newVal != null)
        textAreaCondition.setText(data.get(buttonText, newVal.toLowerCase()));
//      System.out.println(data.get(buttonText, newVal));
    });
    textAreaCondition.setEditable(false);
    textAreaOutPut.setEditable(false);

    try {
      ParserLaboratories.parserLaboratories();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private void laboratoriesButtonsEvent() {
    Button[] allButtons = {secondButton, thirdButton, fourthButton, fifthButton, sixthButton,
        seventhButton};

    for (var btn : allButtons) {
      buttonConfigurator.configureButton(btn, event -> {
        buttonText = btn.getText().charAt(btn.getText().length()-1) + " лаба";
        comboBoxConfigurator.configureCombobox(comboBox, btn);
      });


    }
  }
  private void buttonEvent() {
    buttonConfigurator.configureButton(btnExit, event -> Platform.exit());
    buttonConfigurator.configureButton(btnBackToMenu, event -> {
      try {
        controllerOfScene.switchToMenu(event);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    buttonConfigurator.configureButton(startButton, event -> {
      var nameLab = comboBoxConfigurator.parseNumToNameOfClass(buttonText.split(" ")[0]);
      var value = comboBox.getValue();

      Class<?> clazz = null;
      for (var key : dictLabs.keySet()) {
        if (key.getName().equals(nameLab)) {
          clazz = key;
        }
      }
      var inputData = textField.getText();
      var comboBoxData = value.split("\\s+")[0];
      textAreaOutPut.setText((String) ConsoleReader.executeTask(clazz, comboBoxData, inputData));
    });

    buttonConfigurator.configureButton(clearButton, event->{
      textAreaOutPut.clear();
      textAreaCondition.clear();
      textField.clear();
    });
  }



}
