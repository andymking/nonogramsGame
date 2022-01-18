package com.comp301.a09nonograms.model;

public class Puzzle {
  // Puzzle class to encapsulate a Clues and Board object of a certain Puzzle.
  private Clues clues;
  private Board board;

  public Puzzle(Clues clues, Board board) {
    this.clues = clues;
    this.board = board;
  }

  public Clues getClues() {
    return this.clues;
  }

  public Board getBoard() {
    return this.board;
  }
}
