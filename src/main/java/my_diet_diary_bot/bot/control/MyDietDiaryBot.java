package my_diet_diary_bot.bot.control;

import my_diet_diary_bot.bot.service.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Component
@PropertySource("classpath:application.properties")
public class MyDietDiaryBot extends TelegramLongPollingBot {
    //@Value("${token}")
    private String token;
    //@Value("${name}")
    private String botUserName;
    @Autowired
    private Controller controller;

    public MyDietDiaryBot(DefaultBotOptions options,
                          @Value("${token}") String token,
                          @Value("${name}") String botUserName) {
        super(options);
        this.token = token;
        this.botUserName = botUserName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage response = (SendMessage) controller.getResponse(message);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
