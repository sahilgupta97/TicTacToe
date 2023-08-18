package strategies.winningStrategies;

import models.Board;
import models.Move;

public class DiagonalWinningStrategy implements WinningStrategy {

  @Override
  public boolean checkWinner(Board board, Move move) {
    return false;
  }

  @Override
  public void handleUndo(Board board, Move move) {

  }
}
