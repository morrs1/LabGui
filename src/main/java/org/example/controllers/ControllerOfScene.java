package org.example.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerOfScene {

  private Stage stage;
  private Scene scene;
  private Parent root;

  public void switchToMainScene(MouseEvent me) throws IOException {
    root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/mainSceneMenu.fxml")));
    stage = (Stage) ((Node) me.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToSubScene(MouseEvent me) throws IOException {
    root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/LaboratoriesFourthSem.fxml")));
    stage = (Stage) ((Node) me.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
