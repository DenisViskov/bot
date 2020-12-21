package my_diet_diary_bot.bot.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
    String ip= "74.125.205.102";
    try {
      InetAddress inet = InetAddress.getByName(ip);
      inet.isReachable(5000);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
