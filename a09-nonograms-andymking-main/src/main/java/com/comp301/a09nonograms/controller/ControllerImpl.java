package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import java.util.concurrent.ThreadLocalRandom;

public class ControllerImpl implements Controller {
  // Controller implementation for MVC.
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public Clues getClues() {
    return this.model.getClues();
  }

  @Override
  public boolean isSolved() {
    return this.model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return this.model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return this.model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    this.model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    this.model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    this.model.setPuzzleIndex(this.model.getPuzzleIndex() + 1);
  }

  @Override
  public void prevPuzzle() {
    this.model.setPuzzleIndex(this.model.getPuzzleIndex() - 1);
  }

  @Override
  public void randPuzzle() {
    int rand = ThreadLocalRandom.current().nextInt(0, this.model.getPuzzleCount());
    if (rand == this.model.getPuzzleIndex()) {
      rand--;
    }
    if (rand < 0) {
      rand += 2;
    }
    this.model.setPuzzleIndex(rand);
  }

  @Override
  public void clearBoard() {
    this.model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return this.model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return this.model.getPuzzleCount();
  }
}
