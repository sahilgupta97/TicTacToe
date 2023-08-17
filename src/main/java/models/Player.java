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
        System.out.println("Please tell row count where you want to move (Starting from 0)");
        int row = scanner.nextInt();

        System.out.println("Please tell column count where you want to move (Starting from 0)");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
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
