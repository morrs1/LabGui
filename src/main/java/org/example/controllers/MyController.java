package org.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MyController implements Initializable {
  @FXML
  private Button BtnExit;
  @FXML
  private Button goTo4thSem;

  private void buttonExitEvent(){
    BtnExit.setOnMouseClicked(x-> Platform.exit());

  }
private void setGoTo4thSem(){
    goTo4thSem.setOnMouseClicked(event -> {
      try {
        new ControllerOfScene().switchToSubScene(event);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
}
  @Override
  public void initialize(URL location, ResourceBundle resources) {
  buttonExitEvent();
  setGoTo4thSem();
  }


}
