package org.example.controllers;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.example.util.ButtonConfigurator;
import org.example.util.ComboBoxConfigurator;
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
  private final ComboBoxConfigurator comboBoxConfigurator = new ComboBoxConfigurator();
  @FXML
  private Button secondButton, thirdButton, fourthButton, fifthButton, sixthButton, seventhButton;
  @FXML
  private ComboBox<String> comboBox;

  public LaboratoriesFourthSem() throws ClassNotFoundException {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    laboratoriesButtonsEvent();
    buttonEvent();
    comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {

      if (newVal != null)
        textAreaCondition.setText(data.get(buttonText, newVal.toLowerCase()));
      System.out.println(data.get(buttonText, newVal));
    });
    textAreaCondition.setEditable(false);
    textAreaOutPut.setEditable(false);
    data.get("2 лаба", "5 задание");
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
//         buttonText = btn.getText();

        buttonText = btn.getText().charAt(btn.getText().length()-1) + " лаба";
        System.out.println(buttonText);
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
  }



}
