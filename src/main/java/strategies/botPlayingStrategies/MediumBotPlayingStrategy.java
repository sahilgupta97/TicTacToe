package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.CellStatus;
import models.Move;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {

  @Override
  public Move makeMove(Board board) {
    int boardSize = board.getSize();
    int row = (int) (Math.random() * 10007) % boardSize;
    int col = (int) (Math.random() * 10007) % boardSize;

    Cell[][] boardGrid = board.getBoard();
    Cell currentCell = boardGrid[row][col];

    while (!currentCell.getCellStatus().equals(CellStatus.VACANT)) {
      row = (int) (Math.random() * 10007) % boardSize;
      col = (int) (Math.random() * 10007) % boardSize;

      currentCell = boardGrid[row][col];
    }

    return new Move(null, currentCell);
  }
}
