package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.CellStatus;
import models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

  @Override
  public Move makeMove(Board board) {
    Cell[][] boardGrid = board.getBoard();
    for (Cell[] row : boardGrid) {
      for (Cell cell : row) {
        if (cell.getCellStatus().equals(CellStatus.VACANT)) {
          return new Move(null, cell);
        }
      }
    }
    return null;
  }
}
