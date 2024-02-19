package org.example.controllers;


import java.awt.TextArea;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class ControllerOfScene {

  private boolean flagWindowCreated;
  private double xOffset, yOffset;
  private Stage stage;
  private Parent root;
  private Scene menu, laboratories4thSem, laboratories5thSem, laboratories6thSem;
  public ControllerOfScene(Stage stage) {
    this.stage = stage;
  }

  public ControllerOfScene() {
  }

  public void createAllScenes(MouseEvent event) throws IOException {
    try {
      menu = createWindow(event, "/mainSceneMenu.fxml");
      laboratories4thSem = createWindow(event, "/LaboratoriesFourthSem.fxml");
      laboratories5thSem = createWindow(event, "/LaboratoriesFifthSem.fxml");
      laboratories6thSem = createWindow(event, "/LaboratoriesFourthSem.fxml");
    } catch (IOException e) {
      throw new RuntimeException("Неправильные файлы или event");
    }

    this.flagWindowCreated = true;
  }


  private Scene createWindow(MouseEvent event, String filePath) throws IOException {
    root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource(filePath)));
    var scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    root.setOnMousePressed(ev -> {
      xOffset = ev.getSceneX();
      yOffset = ev.getSceneY();
    });

    root.setOnMouseDragged(ev -> {
      stage.setX(ev.getScreenX() - xOffset);
      stage.setY(ev.getScreenY() - yOffset);
    });

    return scene;
  }

  private Scene createWindow() throws IOException {
    root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/mainSceneMenu.fxml")));
    var scene = new Scene(root);

    scene.setFill(Color.TRANSPARENT);

    root.setOnMousePressed(ev -> {
      xOffset = ev.getSceneX();
      yOffset = ev.getSceneY();
    });

    root.setOnMouseDragged(ev -> {
      this.stage.setX(ev.getScreenX() - xOffset);
      this.stage.setY(ev.getScreenY() - yOffset);
    });

    return scene;
  }



  public void createMenu() throws IOException {

    if (!flagWindowCreated) {
      this.menu = createWindow();
    }

    this.stage.setScene(this.menu);
  }

  public void switchToMenu(MouseEvent event) throws IOException {

    if (!flagWindowCreated) {
      createAllScenes(event);
    }
    this.stage.setScene(this.menu);


  }

  public void switchFromMenuToLaboratoriesFourthSem(MouseEvent event) throws IOException {

    if (!flagWindowCreated) {
      createAllScenes(event);
    }
    this.stage.setScene(this.laboratories4thSem);
  }
  public void switchFromMenuToLaboratoriesFifthSem(MouseEvent event) throws IOException {

    if (!flagWindowCreated) {
      createAllScenes(event);
    }
    this.stage.setScene(this.laboratories5thSem);

  }
  public void switchFromMenuToLaboratoriesSixthSem(MouseEvent event) throws IOException {

    if (!flagWindowCreated) {
      createAllScenes(event);
    }
    this.stage.setScene(this.laboratories6thSem);

  }
}
