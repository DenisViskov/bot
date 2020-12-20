package my_diet_diary_bot.bot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import my_diet_diary_bot.bot.service.Resolver;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Component
@EnableAsync
@PropertySource("classpath:application.properties")
public class MyDietDiaryBot extends TelegramLongPollingBot {
    @Value("${token}")
    private String token;
    @Value("${name}")
    private String botUserName;
    @Autowired
    private Resolver resolver;

    @Async
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage response = (SendMessage) resolver.resolveCommand(message);
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
