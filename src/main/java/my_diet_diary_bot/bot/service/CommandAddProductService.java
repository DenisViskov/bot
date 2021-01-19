package my_diet_diary_bot.bot.service;

import my_diet_diary_bot.bot.domain.Person;
import my_diet_diary_bot.bot.repository.PersonRepository;
import my_diet_diary_bot.bot.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Service
public class CommandAddProductService implements Command<SendMessage, Message> {
    private final PersonRepository personRepository;
    private final ProductRepository productRepository;

    public CommandAddProductService(PersonRepository personRepository, ProductRepository productRepository) {
        this.personRepository = personRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public SendMessage executeCommand(Message message) {
        Optional<Person> box = personRepository.findByChatId(message.getChatId());
        Person person = box.orElse(personRepository.save(new Person(message.getChatId(),
                message.getChat().getUserName(),
                ModeTypes.ADD,
                null)));
        if(person.getLastAction() == null){

        }
    }

    private SendMessage lastActionNull(Message message){
        SendMessage result = new SendMessage();
        result.setChatId(String.valueOf(message.getChatId()));
        String text = message.getText();
        if(text.equals(Commands.ADD_PRODUCT.getUserCommand())){
            result.setText("Введите пожалуйста имя продукта:");
        } else {
            Person person = personRepository.findByChatId(message.getChatId()).get();

        }
    }
}
