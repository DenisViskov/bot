package my_diet_diary_bot.bot.domain;

import my_diet_diary_bot.bot.service.Actions;
import my_diet_diary_bot.bot.service.ModeTypes;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 21.12.2020
 */
public class Person {
  private long id;
  private long chatId;
  private String userName;
  private ModeTypes modeType;
  private Actions last_action;
}
