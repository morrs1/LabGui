package org.example.controllers;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.example.util.ButtonConfigurator;
import org.example.util.ParserConditions;


public class LaboratoriesFourthSem implements Initializable {

  @FXML
  private Button btnExit, btnBackToMenu;
  @FXML
  private TextArea textAreaCondition, textAreaOutPut;
  private final ControllerOfScene controllerOfScene = new ControllerOfScene();
  private final ButtonConfigurator buttonConfigurator = new ButtonConfigurator();
  private final ParserConditions data = new ParserConditions();
  @FXML
  private Button secondButton, thirdButton, fourthButton, fifthButton, sixthButton, seventhButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    buttonEvent();
    laboratoriesButtonsEvent();

    textAreaCondition.setEditable(false);
    textAreaOutPut.setEditable(false);
    data.get("2 лаба", "5 задание");
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

  private void laboratoriesButtonsEvent() {
    Button[] allButtons = {secondButton, thirdButton, fourthButton, fifthButton, sixthButton,
        seventhButton};

    for (var btn : allButtons) {
      buttonConfigurator.configureButton(btn, event -> {
        var buttonText = btn.getText();
        System.out.println(buttonText);
      });
    }
  }

}
