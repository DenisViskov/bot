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
public class StartService implements Worker<Message, SendMessage> {

    @Override
    public SendMessage executeCommand(Message message) {
        SendMessage result = new SendMessage();
        result.setText(greeting(message.getChat().getUserName()));
        return result;
    }

    private String greeting(String userName) {
        String introduction = "Hello my dear " + userName + " ! "
                + "In that chat bot available calculating the amount of food consuming.";
        String add_food = "/"
                + Command.ADD_FOOD
                .name() + " - " + "add new product to bucket";
        String replace_food = "/"
                + Command.REPLACE_FOOD
                .name() + " - " + "replace product in bucket by given name";
        String delete_food = "/"
                + Command.DELETE_FOOD
                .name() + " - " + "delete product from bucket by given name";
        String result = introduction + System.lineSeparator()
                + "Description about commands:"
                + System.lineSeparator()
                + add_food + System.lineSeparator()
                + replace_food + System.lineSeparator()
                + delete_food;
        return result;
    }
}
