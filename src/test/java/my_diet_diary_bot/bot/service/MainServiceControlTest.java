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
    void getResponseStart() {
        MainServiceControl control = new MainServiceControl(new StartService());
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(message.getText()).thenReturn("/start");
        when(message.getChat()).thenReturn(chat);
        when(message.getChatId()).thenReturn(123l);
        when(chat.getUserName()).thenReturn("DenisViskov");

        SendMessage response = control.getResponse(message);
        assertEquals(Long.valueOf(response.getChatId()),123l);
    }

    @Test
    void getResponseAdd() {
        MainServiceControl control = new MainServiceControl(new StartService());
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(message.getText()).thenReturn("/ADD_FOOD -p productName -wf 123 -ws 321 -wr 70");
        when(message.getChat()).thenReturn(chat);
        when(message.getChatId()).thenReturn(123l);
        when(chat.getUserName()).thenReturn("DenisViskov");

        SendMessage response = control.getResponse(message);
        assertEquals(Long.valueOf(response.getChatId()),123l);
    }

    @Test
    void getResponseReplace() {
        MainServiceControl control = new MainServiceControl(new StartService());
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(message.getText()).thenReturn("/REPLACE_FOOD -pproductName -wf 123 -ws 321 -wr 70");
        when(message.getChat()).thenReturn(chat);
        when(message.getChatId()).thenReturn(123l);
        when(chat.getUserName()).thenReturn("DenisViskov");

        SendMessage response = control.getResponse(message);
        assertEquals(Long.valueOf(response.getChatId()),123l);
    }
}