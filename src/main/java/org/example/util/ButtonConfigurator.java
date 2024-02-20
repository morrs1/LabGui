package org.example.util;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonConfigurator {

  public void configureButton(Button button, EventHandler<MouseEvent> eventHandler){
    button.setOnMouseClicked(eventHandler);
  }
}
