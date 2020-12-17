package my_diet_diary_bot.bot.service;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
public interface Controller<T,V> {
    public V getResponse(T message);
}
