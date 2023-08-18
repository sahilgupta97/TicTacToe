package controller;

import exception.MoreThanOneBotException;
import exception.PlayersCountDimensionMismatchException;
import exception.SymbolDuplicacyException;
import models.Game;
import models.GameStatus;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

  public Game createGame(int boardSize, List<Player> players, List<WinningStrategy> winningStrategies)
      throws SymbolDuplicacyException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
    return Game.builder().setBoardSize(boardSize).setPlayers(players).setWinningStrategies(winningStrategies)
        .build();
  }

  public void displayBoard(Game game) {
    game.displayBoard();
  }

  public void undo(Game game) {
    game.undo();
  }

  public void makeMove(Game game) {
    game.makeMove();
  }

  public GameStatus getGameStatus(Game game) {
    return game.getGameStatus();
  }

  public Player getWinner(Game game) {
    return game.getWinner();
  }
}
