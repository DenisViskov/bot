package my_diet_diary_bot.bot.service;

/**
 * Enum
 *
 * @author Денис Висков
 * @version 1.0
 * @since 17.12.2020
 */
public enum Command {
    ADD_FOOD("/ADD_FOOD"),
    REPLACE_FOOD("/REPLACE_FOOD"),
    DELETE_FOOD("/DELETE_FOOD"),
    ;

    private final String option;

    Command(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
