package my_diet_diary_bot.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Service
public class MainServiceControl implements Controller<Message, SendMessage> {
    private final Worker start;

    @Autowired
    public MainServiceControl(@Qualifier("startService") Worker start) {
        this.start = start;
    }

    @Override
    public SendMessage getResponse(Message message) {
        SendMessage result = new SendMessage();
        String text = message.getText();
        switch (text) {
            case "/start":
                result = (SendMessage) start.executeCommand(message);
                break;
            case "/ADD_FOOD":
                break;
            case "/REPLACE_FOOD":
                break;
            case "/DELETE_FOOD":
                break;
        }
        result.setChatId(message.getChatId().toString());
        return result;
    }

    private boolean validationRequest(String text) {
        boolean result = false;
        String[] splitText = text.split(" ");
        if (splitText[0].equals(Command.ADD_FOOD.name())) {
            result = text.contains(" -p ")
                    && text.contains(" -wf ")
                    && text.contains(" -ws ")
                    && text.contains(" -wr ") ? true : false;
        }
        if (splitText[0].equals(Command.REPLACE_FOOD.name())) {
            result = text.contains(" -p ")
                    && (text.contains(" -wf ")
                    || text.contains(" -ws ")
                    || text.contains(" -wr ")) ? true : false;
        }
        if (splitText[0].equals(Command.DELETE_FOOD.name())) {
            result = text.contains(" -p ") ? true : false;
        }
        return result;
    }


}
