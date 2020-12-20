package my_diet_diary_bot.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import my_diet_diary_bot.bot.utils.WeightCounter;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
@Service
public class CalculatorService implements Command<SendMessage, Message> {

  @Override
  public SendMessage executeCommand(Message message) {
    SendMessage result = new SendMessage();
    result.setChatId(String.valueOf(message.getChatId()));
    result.setText(buildResponse(message.getText()));
    return result;
  }

  private String buildResponse(String command) {
    String[] splitCommand = command.split(" ");
    double rawWeight = Double.parseDouble(splitCommand[0]);
    double readyWeight = Double.parseDouble(splitCommand[1]);
    double oneConsumeWeight = Double.parseDouble(splitCommand[2]);

    double result = WeightCounter.countResultWeight(rawWeight, readyWeight, oneConsumeWeight);
    return "Результат расчёта:" + System.lineSeparator()
        + "  -- общий вес в сыром виде:" + " " + rawWeight + System.lineSeparator()
        + "  -- общий вес в готовом виде:" + " " + readyWeight + System.lineSeparator()
        + "  -- вес на порцию в сыром виде:" + " " + oneConsumeWeight + System.lineSeparator()
        + "  -- вес на порцию в готовом виде:" + " " + result;
  }

}
