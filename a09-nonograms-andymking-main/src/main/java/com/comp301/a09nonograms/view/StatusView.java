package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatusView implements FXComponent {

  // Class that renders the status of the current puzzle.
  private final Controller controller;

  public StatusView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    // Status is an HBox.
    HBox statusBox = new HBox();
    statusBox.setAlignment(Pos.CENTER);
    if (this.controller.isSolved()) {
      // If the puzzle is solved, display solved info.
      int currentPuzzle = this.controller.getPuzzleIndex() + 1;
      int puzzleCount = this.controller.getPuzzleCount();
      Label solvedLabel = new Label("Puzzle " + currentPuzzle + " of " + puzzleCount + " Solved!");
      solvedLabel.setFont(new Font("Comic Sans MS", 20));
      solvedLabel.setTextFill(Color.AQUA);
      statusBox.getChildren().add(solvedLabel);
    } else {
      // If the puzzle is not solved, display puzzle number info.
      int currentPuzzle = this.controller.getPuzzleIndex() + 1;
      int puzzleCount = this.controller.getPuzzleCount();
      Label statusLabel = new Label("Current Puzzle: " + currentPuzzle + " of " + puzzleCount);
      statusLabel.setFont(new Font("Comic Sans MS", 20));
      statusLabel.setTextFill(Color.GRAY);
      statusBox.getChildren().add(statusLabel);
    }
    return statusBox;
  }
}
