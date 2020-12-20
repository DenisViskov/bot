package my_diet_diary_bot.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
@Service
public class HelperService implements Command<SendMessage, Message> {

  @Override
  public SendMessage executeCommand(Message message) {
    SendMessage result = new SendMessage();
    result.setChatId(String.valueOf(message.getChatId()));
    result.setText(buildHelpMessage(message.getChat().getUserName()));
    return result;
  }

  private String buildHelpMessage(String userName) {
    return userName + " кажется ты неправильно вводишь команду!" + System.lineSeparator()
        + "Давай я тебе помогу:" + System.lineSeparator()
        + "1.Для расчёта потребления количества еды на порцию, просто вбей через пробел три показателя:"
        + System.lineSeparator()
        + "  общий вес продукта в сыром виде;" + System.lineSeparator()
        + "  общий вес продукта в готовом виде;" + System.lineSeparator()
        + "  вес на одну порцию в сыром виде." + System.lineSeparator()
        + "(пример команды: 1150 780 70)" + System.lineSeparator()
        + "*все веса указываются в граммах";
  }
}
