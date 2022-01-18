package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import com.comp301.a09nonograms.model.ModelObserver;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    // Launcher of the GUI
    List<Clues> library = PuzzleLibrary.create(); // Get the provided puzzles.
    Model model = new ModelImpl(library); // Create model with the puzzles.
    Controller controller = new ControllerImpl(model); // Create controller with the model.
    // A list of FXComponents is created to allow iteration during
    // a new render.
    List<FXComponent> components = new ArrayList<>();
    // Create and add each FXComponent to the list.
    ControlView controlView = new ControlView(controller);
    StatusView statusView = new StatusView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    components.add(controlView);
    components.add(statusView);
    components.add(puzzleView);
    // Exit button created in App class due to the fact that it
    // never needs to change.
    Button exit = new Button("EXIT");
    exit.setOnAction(
        (actionEvent) -> {
          Platform.exit();
        });
    // GUI is contained in a VBox, which is contained in a scene
    // of size 500x400.
    VBox mainPane = new VBox(8);
    // Add children in specific order.
    mainPane.getChildren().add(exit);
    mainPane.getChildren().add(controlView.render());
    mainPane.getChildren().add(statusView.render());
    mainPane.getChildren().add(puzzleView.render());
    Scene mainScene = new Scene(mainPane, 500, 400);
    stage.setScene(mainScene);
    // ModelObserver class acts as the render method for the entire
    // GUI. When update is called, the mainPain is cleared,
    // and each FXComponent is rendered and replaced in the same
    // order.
    ModelObserver observer =
        model1 -> {
          mainPane.getChildren().clear();
          mainPane.getChildren().add(exit);
          for (FXComponent fxc : components) {
            mainPane.getChildren().add(fxc.render());
          }
        };
    model.addObserver(observer);
    stage.setTitle("Let's play Nonograms");
    stage.show();
  }
}
