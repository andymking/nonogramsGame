package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {
  private int rows;
  private int columns;
  private int[][] rowClues;
  private int[][] colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    // Initialize amount of rows, columns, and references to each array.
    this.rows = rowClues.length;
    this.columns = colClues.length;
    this.rowClues = rowClues;
    this.colClues = colClues;
  }

  public int getWidth() {
    // Returns width of puzzle.
    return this.columns;
  }

  public int getHeight() {
    // Returns height of puzzle.
    return this.rows;
  }

  public int[] getRowClues(int index) {
    // Returns the clues at a certain row.
    return this.rowClues[index];
  }

  public int[] getColClues(int index) {
    // Returns the clues at a certain column.
    return this.colClues[index];
  }

  public int getRowCluesLength() {
    // Returns the length of the row arrays.
    return this.rowClues[0].length;
  }

  public int getColCluesLength() {
    // Returns the length of the column arrays.
    return this.colClues[0].length;
  }
}
