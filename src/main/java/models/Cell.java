package models;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellStatus cellStatus;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.VACANT;
    }

    public void display() {
        if (player == null) {
            System.out.print("| - |");
        } else {
            System.out.print("| " + player.getSymbol().getSymbolChar() + " |");
        }
    }
}
