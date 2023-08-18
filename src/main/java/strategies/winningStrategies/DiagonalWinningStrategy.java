package strategies.winningStrategies;

import java.util.HashMap;
import java.util.Map;
import models.Board;
import models.Move;
import models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {

  Map<Symbol, Integer> leftDiagonalWinnerTable = new HashMap<>();
  Map<Symbol, Integer> rightDiagonalWinnerTable = new HashMap<>();

  @Override
  public boolean checkWinner(Board board, Move move) {
    int row = move.getCell().getRow();
    int col = move.getCell().getCol();

    int boardSize = board.getSize();
    Symbol playerSymbol = move.getPlayer().getSymbol();

    // left diagonal
    if (row + col == boardSize - 1) {
      leftDiagonalWinnerTable.put(playerSymbol, leftDiagonalWinnerTable.getOrDefault(playerSymbol, 0) + 1);
    }

    // right diagonal
    if (row == col) {
      rightDiagonalWinnerTable.put(playerSymbol, rightDiagonalWinnerTable.getOrDefault(playerSymbol, 0) + 1);
    }

    boolean winningByLeftDiagonal = leftDiagonalWinnerTable.getOrDefault(playerSymbol, 0) == boardSize;
    boolean winningByRightDiagonal = rightDiagonalWinnerTable.getOrDefault(playerSymbol, 0) == boardSize;
    return winningByLeftDiagonal || winningByRightDiagonal;
  }

  @Override
  public void handleUndo(Board board, Move move) {
    int row = move.getCell().getRow();
    int col = move.getCell().getCol();

    int boardSize = board.getSize();
    Symbol playerSymbol = move.getPlayer().getSymbol();

    // left diagonal
    if (row + col == boardSize - 1) {
      leftDiagonalWinnerTable.put(playerSymbol, leftDiagonalWinnerTable.getOrDefault(playerSymbol, 0) + 1);
    }

    // right diagonal
    if (row == col) {
      rightDiagonalWinnerTable.put(playerSymbol, rightDiagonalWinnerTable.getOrDefault(playerSymbol, 0) + 1);
    }
  }
}
