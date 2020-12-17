package my_diet_diary_bot.bot.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        System.out.println(message.getText());
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
