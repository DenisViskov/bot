package my_diet_diary_bot.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
@Service
public class StartService implements Worker<Message, SendMessage> {

    @Override
    public SendMessage executeCommand(Message message) {
        SendMessage result = new SendMessage();
        result.setText(greeting(message.getChat().getUserName()));
        result.setReplyMarkup(getKeyboard());
        return result;
    }

    private String greeting(String userName) {
        String introduction = "Hello my dear " + userName + " ! "
                + "In that chat bot available calculating the amount of food consuming.";
        String add_food = Command.ADD_FOOD.getOption() + " - " + "add new product to bucket";
        String replace_food = Command.REPLACE_FOOD.getOption() + " - " + "replace product in bucket by given name";
        String delete_food = Command.DELETE_FOOD.getOption() + " - " + "delete product from bucket by given name";
        String result = introduction + System.lineSeparator()
                + "Description about commands:"
                + System.lineSeparator()
                + add_food + System.lineSeparator()
                + replace_food + System.lineSeparator()
                + delete_food;
        return result;
    }

    private ReplyKeyboardMarkup getKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(Command.ADD_FOOD.getOption());
        keyboardFirstRow.add(Command.REPLACE_FOOD.getOption());
        keyboardFirstRow.add(Command.DELETE_FOOD.getOption());

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(Options.p.getOption());
        keyboardSecondRow.add(Options.wf.getOption());
        keyboardSecondRow.add(Options.ws.getOption());
        keyboardSecondRow.add(Options.wr.getOption());

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
