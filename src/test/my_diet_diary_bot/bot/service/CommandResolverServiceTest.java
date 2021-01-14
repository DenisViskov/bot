package my_diet_diary_bot.bot.service;

import my_diet_diary_bot.bot.BotApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BotApplication.class)
class CommandResolverServiceTest {

    @MockBean(classes = HelperService.class)
    private Command helper;
    @MockBean(classes = CalculatorService.class)
    private Command calculator;
    @Autowired
    private CommandResolverService service;


    @Test
    void whenWeHaveShortCommandTest() {
        SendMessage result = new SendMessage();
        result.setText("exactly");
        Message message = mock(Message.class);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("1234 1234 1234");
        when(calculator.executeCommand(message)).thenReturn(result);
        SendMessage expected = service.resolveCommand(message);
        assertEquals(expected.getText(),"exactly");
    }

    @Test
    void whenWeHaveEmptyCommandTest() {
        SendMessage result = new SendMessage();
        result.setText("oops");
        Message message = mock(Message.class);
        when(message.hasText()).thenReturn(false);
        when(message.getText()).thenReturn("");
        when(helper.executeCommand(message)).thenReturn(result);
        SendMessage expected = service.resolveCommand(message);
        assertEquals(expected.getText(),"oops");
    }
}