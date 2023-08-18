package strategies.winningStrategies;

import java.util.HashMap;
import java.util.Map;
import models.Board;
import models.Move;
import models.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {

  Map<Symbol, int[]> columnWinnerTable = new HashMap<>();

  @Override
  public boolean checkWinner(Board board, Move move) {
    int col = move.getCell().getCol();
    Symbol playerSymbol = move.getPlayer().getSymbol();
    int boardSize = board.getSize();

    if (!columnWinnerTable.containsKey(playerSymbol)) {
      columnWinnerTable.put(playerSymbol, new int[boardSize]);
    }

    int[] playerColFreqTable = columnWinnerTable.get(playerSymbol);
    playerColFreqTable[col] += 1;
    int playerColFreq = playerColFreqTable[col];

    return playerColFreq == boardSize;
  }

  @Override
  public void handleUndo(Board board, Move move) {
    int col = move.getCell().getCol();
    Symbol playerSymbol = move.getPlayer().getSymbol();

    int[] playerColFreqTable = columnWinnerTable.get(playerSymbol);
    playerColFreqTable[col] -= 1;
  }
}
