package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  // Main model class for the game.
  private final List<Puzzle> puzzles; // List of all puzzles.
  private int currentPuzzle; // Index to keep track of the current puzzle.
  private final List<ModelObserver> observers = new ArrayList<>(); // List of observers.

  public ModelImpl(List<Clues> clues) {
    this.puzzles = new ArrayList<>();
    for (Clues c : clues) {
      // Initialize puzzles with new Puzzle objects according to each Clues object
      // in the given list of Clues.
      this.puzzles.add(new Puzzle(c, new BoardImpl(c)));
    }
    this.currentPuzzle = 0;
  }

  @Override
  public boolean isShaded(int row, int col) {
    // Returns true if the cell on the Board object is shaded.
    return this.puzzles.get(this.currentPuzzle).getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    // Returns true if the cell on the Board object is eliminated.
    return this.puzzles.get(this.currentPuzzle).getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    // Returns true if the cell on the Board object is blank.
    return this.puzzles.get(this.currentPuzzle).getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    // Changes the given cell to shaded.
    this.puzzles.get(this.currentPuzzle).getBoard().toggleCellShaded(row, col);
    for (ModelObserver obs : this.observers) {
      obs.update(this);
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    // Changes the given cell to eliminated.
    this.puzzles.get(this.currentPuzzle).getBoard().toggleCellEliminated(row, col);
    for (ModelObserver obs : this.observers) {
      obs.update(this);
    }
  }

  @Override
  public void clear() {
    // Sets every cell in the board to blank.
    this.puzzles.get(this.currentPuzzle).getBoard().clear();
    for (ModelObserver obs : this.observers) {
      obs.update(this);
    }
  }

  @Override
  public int getWidth() {
    // Returns the width of the puzzle.
    return this.puzzles.get(this.currentPuzzle).getClues().getWidth();
  }

  @Override
  public int getHeight() {
    // Returns the height of the puzzle.
    return this.puzzles.get(this.currentPuzzle).getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    // Returns the array of row clues at the given index.
    return this.puzzles.get(this.currentPuzzle).getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    // Returns the array of the column clues at the given index.
    return this.puzzles.get(this.currentPuzzle).getClues().getColClues(index);
  }

  @Override
  public Clues getClues() {
    return this.puzzles.get(this.currentPuzzle).getClues();
  }

  @Override
  public int getRowCluesLength() {
    // Returns the length of the row clues arrays (constant across all).
    return this.puzzles.get(this.currentPuzzle).getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    // Returns the length of the column clues arrays (constant across all).
    return this.puzzles.get(this.currentPuzzle).getClues().getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    // Returns the amount of available puzzles in the game.
    return this.puzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    // Returns the puzzle index, corresponding to the current puzzle
    // the player is solving.
    return this.currentPuzzle;
  }

  @Override
  public void setPuzzleIndex(int index) {
    // Sets the puzzle index, or current puzzle.
    if (index < 0 || index >= this.puzzles.size()) {
      throw new IllegalArgumentException("Index less than 0 or larger than Puzzles.");
    } else {
      this.currentPuzzle = index;
    }
    for (ModelObserver obs : this.observers) {
      obs.update(this);
    }
  }

  @Override
  // Adds an observer to the list of observers of the model (view objects).
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    // Removes an observer from the list of observers of the model (view objects).
    this.observers.remove(observer);
  }

  @Override
  public boolean solveColumn(int col) {
    // Algorithm to test if a column is solved.
    // Clues of the specified column are placed in an array.
    int[] clues = this.puzzles.get(this.currentPuzzle).getClues().getColClues(col);
    // The current clues List acts as a list of only the non-zero clues.
    // Clues of int 0 are blank.
    List<Integer> currentClues = new ArrayList<>();
    for (int i : clues) {
      if (i != 0) {
        currentClues.add(i);
      }
    }
    if (currentClues.size() == 0) {
      return true;
    }
    int currentIndex = 0; // Current index is which clue we have found int the series of clues.
    int currentStreak = 0; // Current streak is the streak found in the column.
    for (int i = 0; i < this.puzzles.get(this.currentPuzzle).getClues().getHeight(); i++) {
      // A for loop that goes through each cell in the column.
      if (this.puzzles.get(this.currentPuzzle).getBoard().isShaded(i, col)) {
        // If the cell is shaded, add to our current streak.
        currentStreak++;
        if (i == this.puzzles.get(this.currentPuzzle).getClues().getHeight() - 1) {
          // Case in which last cell is part of valid streak.
          currentIndex++;
          if (currentStreak != currentClues.get(currentClues.size() - 1)) {
            // If last cell is part of a streak, and said streak is not equal
            // to the last clues in currentClues, then the column does not follow
            // the rules of the game.
            return false;
          }
        }
      } else if (currentStreak > 0) {
        // If the cell is not shaded and the current streak is not 0, then we have
        // found a block of cells to compare to currentClues.
        if (currentIndex >= currentClues.size()) {
          return false;
        }
        if (currentStreak != currentClues.get(currentIndex)) {
          // If the streak is different from a clue or currentIndex is
          // larger than the size of the currentClues array, the column
          // does not follow the rules of the game.
          return false;
        }
        currentStreak = 0;
        currentIndex++;
      }
    }
    return currentIndex == currentClues.size();
  }

  @Override
  public boolean solveRow(int row) {
    // Algorithm to test if a column is solved.
    // Clues of the specified column are placed in an array.
    int[] clues = this.puzzles.get(this.currentPuzzle).getClues().getRowClues(row);
    // The current clues List acts as a list of only the non-zero clues.
    List<Integer> currentClues = new ArrayList<>();
    for (int i : clues) {
      if (i != 0) {
        currentClues.add(i);
      }
    }
    if (currentClues.size() == 0) {
      return true;
    }
    int currentIndex = 0; // Current index is which clue we have found.
    int currentStreak = 0; // Current streak is the streak found in the column.
    for (int i = 0; i < this.puzzles.get(this.currentPuzzle).getClues().getWidth(); i++) {
      // A for loop that goes through each cell in the column.
      if (this.puzzles.get(this.currentPuzzle).getBoard().isShaded(row, i)) {
        // If the cell is shaded, add to our current streak.
        currentStreak++;
        if (i == this.puzzles.get(this.currentPuzzle).getClues().getWidth() - 1) {
          // Case in which last cell is part of valid streak.
          currentIndex++;
          if (currentStreak != currentClues.get(currentClues.size() - 1)) {
            // If last cell is part of a streak, and said streak is not equal
            // to the last clues in currentClues, then the row does not follow
            // the rules of the game.
            return false;
          }
        }
      } else if (currentStreak > 0) {
        // If the cell is not shaded and the current streak is not 0, then we have
        // found a block of cells to compare to currentClues.
        if (currentIndex >= currentClues.size()) {
          return false;
        }
        if (currentStreak != currentClues.get(currentIndex)) {
          // If the streak is different from a clue or currentIndex is
          // larger than the size of the currentClues array, the row
          // does not follow the rules of the game.
          return false;
        }
        currentStreak = 0;
        currentIndex++;
      }
    }
    return currentIndex == currentClues.size();
  }

  @Override
  public boolean isSolved() {
    // Function that uses helper functions to see if game is solved.
    for (int i = 0; i < this.puzzles.get(this.currentPuzzle).getClues().getHeight(); i++) {
      // Goes through each row and checks if it is solved.
      if (!solveRow(i)) {
        return false;
      }
    }
    for (int i = 0; i < this.puzzles.get(this.currentPuzzle).getClues().getWidth(); i++) {
      // Goes through each column and checks if it is solved.
      if (!solveColumn(i)) {
        return false;
      }
    }
    return true;
  }
}
