package my_diet_diary_bot.bot.service;

import my_diet_diary_bot.bot.domain.Person;
import my_diet_diary_bot.bot.domain.Product;
import my_diet_diary_bot.bot.repository.PersonRepository;
import my_diet_diary_bot.bot.repository.ProductRepository;
import my_diet_diary_bot.bot.utils.WeightCounter;
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
        SendMessage result = new SendMessage();
        Optional<Person> box = personRepository.findByChatId(message.getChatId());
        Person person = box.orElse(personRepository.save(new Person(message.getChatId(),
                message.getChat().getUserName(),
                ModeTypes.ADD,
                null)));
        if (person.getLastAction() == null) {
            result = lastActionNull(message);
        }
        if (person.getLastAction().equals(Actions.ADD_NAME)) {
            result = lastActionAddName(message);
        }
        if (person.getLastAction().equals(Actions.ADD_RAW_WEIGHT)) {
            result = lastActionRawWeight(message);
        }
        if (person.getLastAction().equals(Actions.ADD_READY_WEIGHT)) {

        }
        return result;
    }

    private SendMessage lastActionNull(Message message) {
        SendMessage result = new SendMessage();
        result.setChatId(String.valueOf(message.getChatId()));
        String text = message.getText();
        if (text.equals(Commands.ADD_PRODUCT.getUserCommand())) {
            result.setText("Введите пожалуйста имя продукта:");
        } else {
            Person person = personRepository.findByChatId(message.getChatId()).get();
            Product product = new Product();
            product.setName(text);
            product.setCurrentEdit(true);
            product = productRepository.save(product);
            person.addProduct(product);
            person.setLastAction(Actions.ADD_NAME);
            personRepository.save(person);
            result.setText("Введите пожалуйста общий вес в сыром виде:");
        }
        return result;
    }

    private SendMessage lastActionAddName(Message message) {
        SendMessage result = new SendMessage();
        result.setChatId(String.valueOf(message.getChatId()));
        String text = message.getText();
        Person person = personRepository.findByChatId(message.getChatId()).get();
        if (text.matches("\\d+")) {
            Optional<Product> editProductBox = person.getProducts()
                    .stream()
                    .filter(product -> product.isCurrentEdit())
                    .findFirst();
            editProductBox.ifPresent(product -> {
                product.setRawWeight(Double.parseDouble(text));
                productRepository.save(product);
                person.addProduct(product);
            });
        }
        person.setLastAction(Actions.ADD_RAW_WEIGHT);
        personRepository.save(person);
        result.setText("Введите пожалуйста общий вес в готовом виде:");
        return result;
    }

    private SendMessage lastActionRawWeight(Message message) {
        SendMessage result = new SendMessage();
        result.setChatId(String.valueOf(message.getChatId()));
        String text = message.getText();
        Person person = personRepository.findByChatId(message.getChatId()).get();
        if (text.matches("\\d+")) {
            Optional<Product> editProductBox = person.getProducts()
                    .stream()
                    .filter(product -> product.isCurrentEdit())
                    .findFirst();
            editProductBox.ifPresent(product -> {
                product.setReadyWeight(Double.parseDouble(text));
                productRepository.save(product);
                person.addProduct(product);
            });
        }
        person.setLastAction(Actions.ADD_READY_WEIGHT);
        personRepository.save(person);
        result.setText("Введите пожалуйста вес на порцию:");
        return result;
    }

    private SendMessage lastActionReadyWeight(Message message) {
        SendMessage result = new SendMessage();
        result.setChatId(String.valueOf(message.getChatId()));
        String text = message.getText();
        Person person = personRepository.findByChatId(message.getChatId()).get();
        if (text.matches("\\d+")) {
            Optional<Product> editProductBox = person.getProducts()
                    .stream()
                    .filter(product -> product.isCurrentEdit())
                    .findFirst();
            editProductBox.ifPresent(product -> {
                product.setOneConsumeWeight(Double.parseDouble(text));
                double resultWeight = WeightCounter.countResultWeight(product.getRawWeight(),
                        product.getReadyWeight(),
                        product.getOneConsumeWeight());
                product.setResultWeight(resultWeight);
                product.setCurrentEdit(false);
                productRepository.save(product);
                person.addProduct(product);
                result.setText("Результат расчёта:" + System.lineSeparator()
                        + "  -- общий вес в сыром виде:" + " " + product.getRawWeight() + System.lineSeparator()
                        + "  -- общий вес в готовом виде:" + " " + product.getReadyWeight() + System.lineSeparator()
                        + "  -- вес на порцию в сыром виде:" + " " + product.getOneConsumeWeight() + System.lineSeparator()
                        + "  -- вес на порцию в готовом виде:" + " " + product.getResultWeight());
            });
        } else {
            result.setText("Готово");
        }
        person.setLastAction(null);
        personRepository.save(person);
        return result;
    }
}
