package my_diet_diary_bot.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Service
public class MainServiceControl implements Controller<Message, SendMessage> {
    @Override
    public SendMessage getResponse(Message message) {
        SendMessage result = new SendMessage();
        result.setChatId(message.getChatId().toString());
        if (!message.hasText()) {
            result.setText("Oops, i think you sent an empty message");
            return result;
        }
        String text = message.getText();
        switch (text) {
            case "/start":
                break;

            case "/add_food":
                break;

            case "/replace_food":
                break;

            case "/delete_food":
                break;
        }
        return result;
    }

    private SendMessage startCommand(){
        return null;
    }


}
