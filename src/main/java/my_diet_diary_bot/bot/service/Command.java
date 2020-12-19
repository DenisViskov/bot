package my_diet_diary_bot.bot.service;

/**
 * Интерфейс реализующий способность
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
public interface Command<T,V> {
  T executeCommand(V message);
}
