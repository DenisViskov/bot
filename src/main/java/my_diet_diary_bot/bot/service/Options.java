package my_diet_diary_bot.bot.service;

/**
 * Enum
 *
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
public enum Options {
    p("-p"),
    wf("-wf"),
    ws("-ws"),
    wr("-wr");

    private final String option;

    Options(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
