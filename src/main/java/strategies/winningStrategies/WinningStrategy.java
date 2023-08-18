package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {

  boolean checkWinner(Board board, Move move);

  void handleUndo(Board board, Move move);
}
