package my_diet_diary_bot.bot.service;

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
public class CommandResolverService implements Resolver<SendMessage, Message>{
  private final Command helper;

  @Autowired
  public CommandResolverService(@Qualifier("helperService") Command helper) {
    this.helper = helper;
  }

  @Override
  public SendMessage resolveCommand(Message message) {
    SendMessage result = new SendMessage();
    result.setChatId(String.valueOf(message.getChatId()));
    if(!validateMessage(message)){
      result = (SendMessage) helper.executeCommand(message);
    }
    return result;
  }

  private boolean validateMessage(Message message){
    if(!message.hasText()){
      return false;
    }
    String text = message.getText();
    if(text.matches("\\A\\d+\\s\\d+\\s\\d+\\Z")){
      return true;
    }
    return false;
  }
}
