package strategies.winningStrategies;

import java.util.HashMap;
import java.util.Map;
import models.Board;
import models.Move;
import models.Symbol;

public class RowWinningStrategy implements WinningStrategy {

  Map<Symbol, int[]> rowWinnerTable = new HashMap<>();

  @Override
  public boolean checkWinner(Board board, Move move) {
    int row = move.getCell().getRow();
    Symbol playerSymbol = move.getPlayer().getSymbol();
    int boardSize = board.getSize();

    if (!rowWinnerTable.containsKey(playerSymbol)) {
      rowWinnerTable.put(playerSymbol, new int[boardSize]);
    }

    int[] playerRowFreqTable = rowWinnerTable.get(playerSymbol);
    playerRowFreqTable[row] += 1;
    int playerRowFreq = playerRowFreqTable[row];

    return playerRowFreq == boardSize;
  }

  @Override
  public void handleUndo(Board board, Move move) {
    int row = move.getCell().getRow();
    Symbol playerSymbol = move.getPlayer().getSymbol();

    int[] playerFreqTable = rowWinnerTable.get(playerSymbol);
    playerFreqTable[row] -= 1;
  }
}
