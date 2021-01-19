package my_diet_diary_bot.bot.service;

import my_diet_diary_bot.bot.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
@Service
public class CommandResolverService implements Resolver<SendMessage, Message> {
    private final Command helper;
    private final Command calculator;

    @Autowired
    public CommandResolverService(@Qualifier("helperService") Command helper,
                                  @Qualifier("calculatorService") Command calculator) {
        this.helper = helper;
        this.calculator = calculator;
    }

    @Override
    public SendMessage resolveCommand(Message message) {
        SendMessage result = new SendMessage();
        if (!validateMessage(message)) {
            result = (SendMessage) helper.executeCommand(message);
        }
        String text = message.getText();
        if (text.matches("\\A\\d+\\s\\d+\\s\\d+\\Z")) {
            result = (SendMessage) calculator.executeCommand(message);
        }
        return result;
    }

    private boolean validateMessage(Message message) {
        if (!message.hasText()) {
            return false;
        }
        String text = message.getText();
        boolean regexBySimpleCounter = text.matches("\\A\\d+\\s\\d+\\s\\d+\\Z");
        boolean coreCommands = text.equals(Commands.ADD_PRODUCT.getUserCommand())
                || text.equals(Commands.EDIT_PRODUCT.getUserCommand())
                || text.equals(Commands.DELETE_PRODUCT.getUserCommand());
        if (regexBySimpleCounter) {
            return true;
        }

        return false;
    }
}
