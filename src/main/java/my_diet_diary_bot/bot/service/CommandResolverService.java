package my_diet_diary_bot.bot.service;

import my_diet_diary_bot.bot.domain.Person;
import my_diet_diary_bot.bot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
@Service
public class CommandResolverService implements Resolver<SendMessage, Message> {
    private final Command helper;
    private final Command calculator;
    private final PersonRepository personRepository;

    @Autowired
    public CommandResolverService(@Qualifier("helperService") Command helper,
                                  @Qualifier("calculatorService") Command calculator,
                                  PersonRepository personRepository) {
        this.helper = helper;
        this.calculator = calculator;
        this.personRepository = personRepository;
    }

    @Override
    public SendMessage resolveCommand(Message message) {
        SendMessage result = new SendMessage();
        Optional<Person> box = personRepository.findByChatId(message.getChatId());
        if (!message.hasText()) {
            result = (SendMessage) helper.executeCommand(message);
        }
        if (message.getText().matches("\\A\\d+\\s\\d+\\s\\d+\\Z")) {
            result = (SendMessage) calculator.executeCommand(message);
        }
        if (message.getText().equals(Commands.ADD_PRODUCT.getUserCommand())
                || message.getText().equals(Commands.EDIT_PRODUCT.getUserCommand())
                || message.getText().equals(Commands.DELETE_PRODUCT.getUserCommand())) {

        }
        if (box.isPresent()) {
            Person person = box.get();
            if (person.getModeType().equals(ModeTypes.ADD)) {

            } else if (person.getModeType().equals(ModeTypes.EDIT)) {

            } else if (person.getModeType().equals(ModeTypes.DELETE)) {

            } else {
                result = (SendMessage) helper.executeCommand(message);
            }
        } else {
            result = (SendMessage) helper.executeCommand(message);
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
        Optional<Person> box = personRepository.findByChatId(message.getChatId());
        boolean hasStatus = box.isPresent() && (box.get().getModeType() != null);
        if (regexBySimpleCounter) {
            return true;
        }
        if (coreCommands) {
            return true;
        }
        if (hasStatus) {
            return true;
        }
        return false;
    }
}
