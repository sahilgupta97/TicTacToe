package models;

public class Board {

  private int size;
  private Cell[][] board;

  public Board(int size) {
    this.size = size;
    this.board = new Cell[size][size];

    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        board[row][col] = new Cell(row, col);
      }
    }
  }

  public void printBoard() {
    for (int row = 0; row < this.size; row++) {
      System.out.println();
      for (int col = 0; col < this.size; col++) {
        Cell cell = board[row][col];
        cell.display();
      }
      System.out.println();
    }
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Cell[][] getBoard() {
    return board;
  }

  public void setBoard(Cell[][] board) {
    this.board = board;
  }
}
