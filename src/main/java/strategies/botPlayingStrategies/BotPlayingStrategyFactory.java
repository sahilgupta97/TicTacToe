package strategies.botPlayingStrategies;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

  public static BotPlayingStrategy createStrategyByDifficultyByLevel(BotDifficultyLevel botDifficultyLevel) {
    BotPlayingStrategy defaultBotPlayingStrategy = new EasyBotPlayingStrategy();

    if (BotDifficultyLevel.EASY.equals(botDifficultyLevel)) {
      return new EasyBotPlayingStrategy();
    } else if (BotDifficultyLevel.MEDIUM.equals(botDifficultyLevel)) {
      return new MediumBotPlayingStrategy();
    } else if (BotDifficultyLevel.HARD.equals(botDifficultyLevel)) {
      return new HardBotPlayingStrategy();
    }

    return defaultBotPlayingStrategy;
  }
}
