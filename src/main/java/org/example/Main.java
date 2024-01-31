package org.example;

import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

  private double xOffset;
  private double yOffset;

  @Override
  public void start(Stage stage) throws Exception {

    Parent root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/mainSceneMenu.fxml")));
    var scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);

    scene.setOnMousePressed(ev -> {
      xOffset = ev.getSceneX();
      yOffset = ev.getSceneY();
    });

    var myStage = initStage(stage, scene);

    scene.setOnMouseDragged(ev -> {
      myStage.setX(ev.getScreenX() - xOffset);
      myStage.setY(ev.getScreenY() - yOffset);
    });

    myStage.show();

  }


  private Stage initStage(Stage primaryStage, Scene scene) {
    primaryStage.getIcons().add(new Image("/Images/free-icon-moon-2530905.png"));
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    return primaryStage;
  }
}