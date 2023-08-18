package models;

import java.util.Scanner;

public class Player {

  private Long id;
  private String name;
  private Symbol symbol;
  private PlayerType playerType;
  private Scanner scanner;

  public Player(Long id, String name, Symbol symbol, PlayerType playerType) {
    this.id = id;
    this.name = name;
    this.symbol = symbol;
    this.playerType = playerType;
    this.scanner = new Scanner(System.in);
  }

  public Move makeMove(Board board) {
    Cell cell = selectCell(board);
    cell.acquire(this);

    return new Move(this, cell);
  }

  private Cell selectCell(Board board) {
    Cell cell = null;
    while (true) {
      board.printBoard();
      System.out.println("Please tell row position where you want to move (Starting from 0)");
      int row = scanner.nextInt();

      System.out.println("Please tell column position where you want to move (Starting from 0)");
      int col = scanner.nextInt();

      cell = board.getCell(row, col);
      if (cell == null || !cell.isOccupiable()) {
        System.out.println("Invalid move played by " + this.getName() + ". Please try again.");
      } else {
        break;
      }
    }

    return cell;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public PlayerType getPlayerType() {
    return playerType;
  }
}
