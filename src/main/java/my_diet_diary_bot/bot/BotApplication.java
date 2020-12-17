package my_diet_diary_bot.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@SpringBootApplication
public class BotApplication {

    @Bean
    public DefaultBotOptions getDefaultBotOptions() {
        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost("192.168.111.102");
        botOptions.setProxyPort(3128);
        // Выбор типа прокси: [HTTP | SOCKS4 | SOCKS5] (по умолчанию: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
        return botOptions;
    }

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

}
