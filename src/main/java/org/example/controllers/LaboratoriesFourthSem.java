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
  private ButtonConfigurator buttonConfigurator = new ButtonConfigurator();

  private final ParserConditions data = new ParserConditions();


//  private void buttonExitEvent() {
//    BtnExit.setOnMouseClicked(event -> Platform.exit());
//
//  }

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
    buttonConfigurator.configureButton(btnExit, event -> Platform.exit());
//    buttonExitEvent();
    backToMenu();
    textAreaCondition.setEditable(false);
    textAreaOutPut.setEditable(false);
    data.get("2 лаба", "5 задание");
  }
}
