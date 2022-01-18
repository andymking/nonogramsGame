package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {

  private final CellState[][] cells;

  public BoardImpl(Clues clues) {
    // Board is array of CellState
    // Initialize cell array with value of BLANK.
    this.cells = new CellState[clues.getHeight()][clues.getWidth()];
    for (int i = 0; i < clues.getHeight(); i++) {
      for (int j = 0; j < clues.getWidth(); j++) {
        this.cells[i][j] = CellState.BLANK;
      }
    }
  }

  public boolean isShaded(int row, int col) {
    // Return true if cell = SHADED.
    return this.cells[row][col] == CellState.SHADED;
  }

  public boolean isEliminated(int row, int col) {
    // Returns true if cell = ELIM.
    return this.cells[row][col] == CellState.ELIM;
  }

  public boolean isSpace(int row, int col) {
    // Returns true if cell = BLANK.
    return this.cells[row][col] == CellState.BLANK;
  }

  public void toggleCellShaded(int row, int col) {
    // If cell value is not SHADED, make it SHADED.
    if (this.cells[row][col] == CellState.SHADED) {
      this.cells[row][col] = CellState.BLANK;
      return;
    } else {
      this.cells[row][col] = CellState.SHADED;
    }
  }

  public void toggleCellEliminated(int row, int col) {
    // If cell value is not ELIM, make it ELIM.
    if (this.cells[row][col] == CellState.ELIM) {
      this.cells[row][col] = CellState.BLANK;
      return;
    } else {
      this.cells[row][col] = CellState.ELIM;
    }
  }

  public void clear() {
    // Runs for loop to set each cell equal to BLANK.
    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        this.cells[i][j] = CellState.BLANK;
      }
    }
  }

  public enum CellState {
    ELIM,
    BLANK,
    SHADED,
  }
}
