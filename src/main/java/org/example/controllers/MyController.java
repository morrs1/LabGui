package org.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.util.ButtonConfigurator;

public class MyController implements Initializable {

  @FXML
  private Button btnExit;
  @FXML
  private Button goTo4thSem;
  private final ButtonConfigurator buttonConfigurator = new ButtonConfigurator();


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    buttonsEvent();
  }

  private void buttonsEvent() {
  buttonConfigurator.configureButton(btnExit, event -> Platform.exit());
  buttonConfigurator.configureButton(goTo4thSem, event -> {
    try {
      new ControllerOfScene().switchFromMenuToLaboratoriesFourthSem(event);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  });
  }

}
