package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {

  // Class that renders the navigation controls of the game.
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    // Controls are contained in an HBox.
    HBox buttonBox = new HBox();
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setSpacing(25);
    if (this.controller.getPuzzleIndex() == 0) {
      // If the puzzle index is 0, then there is no previous
      // option, so the previous button has no function and is
      // transparent. Clear, random, and nextPuzzle remain fully
      // functional.
      Button prevPuzzle = new Button("Prev Puzzle");
      prevPuzzle.setOpacity(0.0);
      Button randomPuzzle = new Button("Random Puzzle");
      randomPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.randPuzzle();
          });
      Button clear = new Button("Clear Board");
      clear.setOnAction(
          (actionEvent) -> {
            this.controller.clearBoard();
          });
      Button nextPuzzle = new Button("Next Puzzle");
      nextPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.nextPuzzle();
          });
      buttonBox.getChildren().add(prevPuzzle);
      buttonBox.getChildren().add(randomPuzzle);
      buttonBox.getChildren().add(clear);
      buttonBox.getChildren().add(nextPuzzle);
    } else if (this.controller.getPuzzleIndex() == this.controller.getPuzzleCount() - 1) {
      // If the puzzle index is the last puzzle, then there is no next
      // option, so the next button has no function and is
      // transparent. Clear, random, and prevPuzzle remain fully
      // functional.
      Button prevPuzzle = new Button("Prev Puzzle");
      prevPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.prevPuzzle();
          });
      Button randomPuzzle = new Button("Random Puzzle");
      randomPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.randPuzzle();
          });
      Button clear = new Button("Clear Board");
      clear.setOnAction(
          (actionEvent) -> {
            this.controller.clearBoard();
          });
      Button nextPuzzle = new Button("Next Puzzle");
      nextPuzzle.setOpacity(0.0);
      buttonBox.getChildren().add(prevPuzzle);
      buttonBox.getChildren().add(randomPuzzle);
      buttonBox.getChildren().add(clear);
      buttonBox.getChildren().add(nextPuzzle);
    } else {
      // If the puzzle index is in the middle of the group,
      // then all buttons are functional and present.
      Button prevPuzzle = new Button("Prev Puzzle");
      prevPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.prevPuzzle();
          });
      Button randomPuzzle = new Button("Random Puzzle");
      randomPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.randPuzzle();
          });
      Button clear = new Button("Clear Board");
      clear.setOnAction(
          (actionEvent) -> {
            this.controller.clearBoard();
          });
      Button nextPuzzle = new Button("Next Puzzle");
      nextPuzzle.setOnAction(
          (actionEvent) -> {
            this.controller.nextPuzzle();
          });
      buttonBox.getChildren().add(prevPuzzle);
      buttonBox.getChildren().add(randomPuzzle);
      buttonBox.getChildren().add(clear);
      buttonBox.getChildren().add(nextPuzzle);
    }
    return buttonBox;
  }
}
