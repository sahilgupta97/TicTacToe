package models;

import exception.MoreThanOneBotException;
import exception.PlayersCountDimensionMismatchException;
import exception.SymbolDuplicacyException;
import strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {

  private Board board;

  private List<Player> players;
  private int currentPlayerIdx;

  private GameStatus gameStatus;
  private Player winner;

  private List<Move> moves;
  private List<WinningStrategy> winningStrategies;

  private Game(int boardSize, List<Player> players, List<WinningStrategy> winningStrategies) {
    this.board = new Board(boardSize);
    this.players = players;
    this.winningStrategies = winningStrategies;
    this.currentPlayerIdx = 0;
    this.gameStatus = GameStatus.IN_PROGRESS;
    this.winner = null;
    this.moves = new ArrayList<>();
  }

  public void displayBoard() {
    board.printBoard();
  }

  public void undo() {
    if (moves.size() == 0) {
      System.out.println("Nothing to undo !");
      return;
    }

    Move lastMove = moves.remove(moves.size() - 1);

    Cell cleanUpCell = lastMove.getCell();
    cleanUpCell.setPlayer(null);
    cleanUpCell.setCellStatus(CellStatus.VACANT);

    for (WinningStrategy winningStrategy : winningStrategies) {
      winningStrategy.handleUndo(getBoard(), lastMove);
    }

    int numberOfPlayers = players.size();
    currentPlayerIdx = (currentPlayerIdx - 1 + numberOfPlayers) % numberOfPlayers;
  }

  public void makeNextMove() {
    Player currentPlayer = players.get(currentPlayerIdx);
    System.out.println("This is " + currentPlayer.getName() + "'s turn. Please make your next move.");

    Move desiredMove = currentPlayer.makeMove(board);
    System.out
        .println(currentPlayer.getName() + " has made a move at position : (" + desiredMove.getCell().getRow()
            + ", " + desiredMove.getCell().getCol() + ").");

    moves.add(desiredMove);

    if (checkWinner(board, desiredMove)) {
      setGameStatus(GameStatus.WIN);
      winner = currentPlayer;
    } else if (moves.size() == (board.getSize() * board.getSize())) {
      setGameStatus(GameStatus.DRAW);
    }

    currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
  }

  private boolean checkWinner(Board board, Move move) {
    for (WinningStrategy winningStrategy : winningStrategies) {
      boolean hasWon = winningStrategy.checkWinner(board, move);
      if (hasWon) {
        return true;
      }
    }

    return false;
  }

  private boolean validateMove(Move move) {
    Cell cell = move.getCell();
    return cell != null && cell.getRow() >= 0 && cell.getRow() < board.getSize() && cell.getCol() >= 0
        && cell.getCol() < board.getSize() && cell.getCellStatus().equals(CellStatus.VACANT);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private int boardSize;
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;

    private Builder() {
      this.boardSize = 0;
      this.players = new ArrayList<>();
      this.winningStrategies = new ArrayList<>();
    }

    public Game build()
        throws SymbolDuplicacyException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
      validate();
      return new Game(boardSize, players, winningStrategies);
    }

    private void validate()
        throws MoreThanOneBotException, PlayersCountDimensionMismatchException, SymbolDuplicacyException {
      validateBotCount();
      validateDimensionsAndPlayerCount();
      validateUniqueSymbolForPlayers();
    }

    private void validateUniqueSymbolForPlayers() throws SymbolDuplicacyException {
      Set<Character> symbolTable = new HashSet<>();

      for (Player player : players) {
        Character currentPlayerSymbol = player.getSymbol().getSymbolChar();
        if (symbolTable.contains(currentPlayerSymbol)) {
          throw new SymbolDuplicacyException("Same symbol defined for multiple players.");
        }

        symbolTable.add(currentPlayerSymbol);
      }
    }

    private void validateDimensionsAndPlayerCount() throws PlayersCountDimensionMismatchException {
      if (players.size() != boardSize - 1) {
        throw new PlayersCountDimensionMismatchException("Board dimensions and player count are not correct");
      }
    }

    private void validateBotCount() throws MoreThanOneBotException {
      int botCount = 0;

      for (Player player : players) {
        if (PlayerType.BOT.equals(player.getPlayerType())) {
          botCount += 1;
        }
      }

      if (botCount > 1) {
        throw new MoreThanOneBotException("Error due to more than one bots defined in game.");
      }
    }

    public int getBoardSize() {
      return boardSize;
    }

    public Builder setBoardSize(int boardSize) {
      this.boardSize = boardSize;
      return this;
    }

    public List<Player> getPlayers() {
      return players;
    }

    public Builder setPlayers(List<Player> players) {
      this.players = new ArrayList<>(players);
      return this;
    }

    public List<WinningStrategy> getWinningStrategies() {
      return winningStrategies;
    }

    public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
      this.winningStrategies = new ArrayList<>(winningStrategies);
      return this;
    }
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public int getCurrentPlayerIdx() {
    return currentPlayerIdx;
  }

  public void setCurrentPlayerIdx(int currentPlayerIdx) {
    this.currentPlayerIdx = currentPlayerIdx;
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(GameStatus gameStatus) {
    this.gameStatus = gameStatus;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public List<Move> getMoves() {
    return moves;
  }

  public void setMoves(List<Move> moves) {
    this.moves = moves;
  }

  public List<WinningStrategy> getWinningStrategies() {
    return winningStrategies;
  }

  public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
    this.winningStrategies = winningStrategies;
  }
}
