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

public class LaboratoriesFourthSem implements Initializable {

  @FXML
  private Button BtnExit;
  @FXML
  private Button btnBackToMenu;
  @FXML
  private TextArea textAreaCondition, textAreaOutPut;
  private final ControllerOfScene controllerOfScene = new ControllerOfScene();

  private void buttonExitEvent() {
    BtnExit.setOnMouseClicked(event -> Platform.exit());

  }

  private void backToMenu() {
    btnBackToMenu.setOnMouseClicked(event -> {
      try {
        controllerOfScene.switchToMenu(event);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    buttonExitEvent();
    backToMenu();
    textAreaCondition.setEditable(false);
    textAreaOutPut.setEditable(false);
  }
}
