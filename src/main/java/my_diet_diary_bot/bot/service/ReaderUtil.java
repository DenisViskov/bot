package my_diet_diary_bot.bot.service;

import java.io.*;
import java.util.StringJoiner;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
public class ReaderUtil {

    public static String getContent(String name) {
        ClassLoader classLoader = ReaderUtil.class.getClassLoader();
        File file = new File(classLoader.getResource(name).getFile());
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .forEach(joiner::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }
}
