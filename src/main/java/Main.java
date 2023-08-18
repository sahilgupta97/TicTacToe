import controller.GameController;
import models.*;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    GameController gameController = new GameController();
    Scanner scanner = new Scanner(System.in);

    try {
      // Create a game
      int boardSize = 3;
      List<Player> players = new ArrayList<>();
      players.add(
          new Player(1L, "Sahil", new Symbol('X'), PlayerType.USER)
      );

      players.add(
          new Bot(2L, "AI", new Symbol('O'), BotDifficultyLevel.HARD)
      );

      List<WinningStrategy> winningStrategies = List.of(
          new RowWinningStrategy(),
          new ColumnWinningStrategy(),
          new DiagonalWinningStrategy()
      );
      Game game = gameController.createGame(boardSize, players, winningStrategies);

      // while game status is in progress
      // print board
      // check whether undo needs to be done
      // if yes call undo
      // move next player
      while (GameStatus.IN_PROGRESS.equals(gameController.getGameStatus(game))) {
        gameController.displayBoard(game);

        System.out.println("Does anyone want to undo ?");
        String undoAnswer = scanner.next();
        if (undoAnswer.equalsIgnoreCase("y")) {
          gameController.undo(game);
        }

        gameController.makeMove(game);
      }

      // check status of the game
      // if winner print winner else draw
      System.out.println("<<< Game is finished >>>");
      GameStatus state = gameController.getGameStatus(game);

      if (GameStatus.DRAW.equals(state)) {
        System.out.println("Game ended in a DRAW");
      } else {
        System.out.println("Winner of the game is " + gameController.getWinner(game).getName());
      }
    } catch (Exception ex) {
      System.out.println("Got exception with message : " + ex.getMessage());
    }
  }
}
