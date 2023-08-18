package models;

import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player {

  private BotDifficultyLevel botDifficultyLevel;
  private BotPlayingStrategy botPlayingStrategy;

  public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
    super(id, name, symbol, PlayerType.BOT);
    this.botDifficultyLevel = botDifficultyLevel;
    this.botPlayingStrategy = BotPlayingStrategyFactory.createStrategyByDifficultyByLevel(botDifficultyLevel);
  }

  @Override
  public Move makeMove(Board board) {
    Move move = botPlayingStrategy.makeMove(board);
    move.setPlayer(this);

    return move;
  }

  public BotDifficultyLevel getBotDifficultyLevel() {
    return botDifficultyLevel;
  }

  public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
    this.botDifficultyLevel = botDifficultyLevel;
  }

  public BotPlayingStrategy getBotPlayingStrategy() {
    return botPlayingStrategy;
  }

  public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
    this.botPlayingStrategy = botPlayingStrategy;
  }
}
