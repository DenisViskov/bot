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
                result.setText(startCommand(message
                        .getChat()
                        .getUserName()));
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

    private String startCommand(String userName) {
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
