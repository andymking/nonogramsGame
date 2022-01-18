package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PuzzleView implements FXComponent {

  // Class that renders the puzzle board.
  private final Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    // Puzzle board is a grid pane.
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_CENTER); // Grid alignment is centered.
    // Ints below are for readability.
    int width = this.controller.getClues().getWidth();
    int height = this.controller.getClues().getHeight();
    int colCluesLength = this.controller.getClues().getColCluesLength();
    int rowCluesLength = this.controller.getClues().getRowCluesLength();
    // Use of helper functions to set the column constraints for formatting.
    this.setColConstraints(grid, width, rowCluesLength);
    this.setRowConstraints(grid, height, colCluesLength);
    for (int col = 0; col < width; col++) {
      // For loop to add the column clues to their respective positions
      // in the grid as labels.
      int row = 0;
      for (int clue : this.controller.getClues().getColClues(col)) {
        // Second for loop iterates through clue items.
        if (clue != 0) {
          String clueString = String.valueOf(clue);
          grid.add(new Label(clueString), col + rowCluesLength, row);
        }
        row++;
      }
    }
    for (int row = 0; row < height; row++) {
      // For loop to add the row clues to their respective positions
      // in the grid as labels.
      int col = 0;
      for (int clue : this.controller.getClues().getRowClues(row)) {
        // Second for loop iterates through clue items.
        if (clue != 0) {
          String clueString = String.valueOf(clue);
          Label label = new Label(clueString);
          grid.add(label, col, row + colCluesLength);
        }
        col++;
      }
    }
    for (int row = colCluesLength; row < colCluesLength + height; row++) {
      // Nested for loop to add the visual aspects representing the playing field,
      // as well as add functionality to those visual aspects.
      // Loop iterates through by row.
      int rowBoard = row - colCluesLength;
      for (int col = rowCluesLength; col < rowCluesLength + width; col++) {
        int colBoard = col - rowCluesLength;
        if (this.controller.isShaded(rowBoard, colBoard)) {
          // If cell is shaded, add a black box to the cell.
          Rectangle rectangle = new Rectangle(18, 18, Color.BLACK);
          // Mouse click and right mouse click
          // are toggle shade and toggle eliminated,
          // respectively.
          rectangle.setOnMouseClicked(
              mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                  this.controller.toggleShaded(rowBoard, colBoard);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                  this.controller.toggleEliminated(rowBoard, colBoard);
                }
              });
          grid.add(rectangle, col, row);
        } else if (this.controller.isEliminated(rowBoard, colBoard)) {
          // If the cell is eliminated, add a grey Rectangle as a background,
          // and a red "X" representing elimination.
          Rectangle rectangle = new Rectangle(18, 18, Color.GRAY);
          rectangle.setOpacity(0.2);
          Label label = new Label("X");
          label.setTextFill(Color.RED);
          label.setOnMouseClicked(
              mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                  this.controller.toggleShaded(rowBoard, colBoard);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                  this.controller.toggleEliminated(rowBoard, colBoard);
                }
              });
          grid.add(rectangle, col, row);
          grid.add(label, col, row);
        } else {
          // If the cell is neither shaded not eliminated,
          // add only the background rectangle.
          Rectangle rectangle = new Rectangle(18, 18, Color.GRAY);
          rectangle.setOpacity(0.2);
          rectangle.setOnMouseClicked(
              mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                  this.controller.toggleShaded(rowBoard, colBoard);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                  this.controller.toggleEliminated(rowBoard, colBoard);
                }
              });
          grid.add(rectangle, col, row);
        }
      }
    }
    return grid;
  }

  private void setColConstraints(GridPane grid, int width, int rowClueSize) {
    // Helper function to set the column constraints of the GridPane.
    for (int i = 0; i < width + rowClueSize; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints();
      columnConstraints.setMinWidth(20);
      columnConstraints.setHalignment(HPos.CENTER);
      grid.getColumnConstraints().add(columnConstraints);
    }
  }

  private void setRowConstraints(GridPane grid, int height, int colClueSize) {
    // Helper function to set the RowConstraints of the GridPane.
    for (int i = 0; i < height + colClueSize; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setMinHeight(20);
      rowConstraints.setValignment(VPos.CENTER);
      grid.getRowConstraints().add(rowConstraints);
    }
  }
}
