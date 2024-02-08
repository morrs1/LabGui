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
import org.example.controllers.ControllerOfScene;

public class Main extends Application {

  private double xOffset;
  private double yOffset;

  @Override
  public void start(Stage stage) throws Exception {
    var stageInitialized = initStage(stage);
    ControllerOfScene controller = new ControllerOfScene(stage);
    controller.createMenu();
    stageInitialized.show();

  }


  private Stage initStage(Stage primaryStage) {
    primaryStage.getIcons().add(new Image("/Images/free-icon-moon-2530905.png"));
    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    return primaryStage;
  }
}