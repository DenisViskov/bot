package my_diet_diary_bot.bot.utils;

import java.io.IOException;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 21.12.2020
 */
@Component
public class GooglePingUtil {

  @Scheduled(fixedRate = 60000)
  public static void pingGoogle() {
    String host = "https://www.google.com/";
    String command = "ping" + " " + host;
    try {
      Runtime.getRuntime().exec(command);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
