package my_diet_diary_bot.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Service
public class MainServiceControl implements Controller<Message, SendMessage> {
    private static String HELP_OPTIONS;
    private final Worker start;

    @Autowired
    public MainServiceControl(@Qualifier("startService") Worker start) {
        this.start = start;
        HELP_OPTIONS = ReaderUtil.getContent("help_options");
    }

    @Override
    public SendMessage getResponse(Message message) {
        SendMessage result = new SendMessage();
        String text = message.getText();
        if (validationRequest(text)) {
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
        } else {
            result.setText(HELP_OPTIONS);
        }
        result.setChatId(message.getChatId().toString());
        return result;
    }

    private boolean validationRequest(String text) {
        boolean result = false;
        String[] splitText = text.split(" ");
        if (text.equals("/start")) {
            result = true;
        }
        if (splitText[0].equals(Command.ADD_FOOD.getOption())) {
            result = text.contains(" -p ")
                    && text.contains(" -wf ")
                    && text.contains(" -ws ")
                    && text.contains(" -wr ");
        }
        if (splitText[0].equals(Command.REPLACE_FOOD.getOption())) {
            result = text.contains(" -p ")
                    && (text.contains(" -wf ")
                    || text.contains(" -ws ")
                    || text.contains(" -wr "));
        }
        if (splitText[0].equals(Command.DELETE_FOOD.getOption())) {
            result = text.contains(" -p ");
        }
        return result;
    }

}
