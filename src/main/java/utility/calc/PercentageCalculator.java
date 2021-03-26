package utility.calc;

public class PercentageCalculator {

    public PercentageCalculator() {
    }

    public static double calculatePercentageWiseOccurrence(double wordCount, double wordsInLexikon) {
        final double resultInPercent = (wordsInLexikon / wordCount) * 100;
        return resultInPercent;
    }
}
