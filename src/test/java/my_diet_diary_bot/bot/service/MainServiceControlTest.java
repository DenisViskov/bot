package my_diet_diary_bot.bot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainServiceControlTest {

    @Test
    void getResponse() {
        MainServiceControl control = new MainServiceControl();
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(message.getChatId()).thenReturn(123l);
        when(message.getText()).thenReturn("/start");
        when(message.hasText()).thenReturn(true);
        when(message.getChat()).thenReturn(chat);
        when(chat.getUserName()).thenReturn("DenisViskov");

        SendMessage result = control.getResponse(message);
    }
}