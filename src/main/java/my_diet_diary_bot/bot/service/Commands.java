package my_diet_diary_bot.bot.service;

/**
 * Enum
 * @author Денис Висков
 * @version 1.0
 * @since 21.12.2020
 */
public enum Commands {
  ADD_PRODUCT("/добавить_продукт"),
  EDIT_PRODUCT("/редактировать"),
  DELETE_PRODUCT("/удалить");

  private final String userCommand;

  Commands(String userCommand) {
    this.userCommand = userCommand;
  }
}
