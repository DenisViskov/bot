package my_diet_diary_bot.bot.utils;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 19.12.2020
 */
public class WeightCounter {

  public static double countResultWeight(double rawWeight, double readyWeight,
      double oneConsumeWeight) {
    double result = readyWeight / (rawWeight / oneConsumeWeight);
    return result;
  }
}
