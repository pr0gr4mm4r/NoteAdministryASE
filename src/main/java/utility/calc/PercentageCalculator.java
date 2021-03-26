package utility.calc;

public class PercentageCalculator {

    public PercentageCalculator() {
    }

    public static double calculatePercentageWiseOccurrence(final double wordCount, final double wordsInLexikon) {
        final double resultInPercent = (wordsInLexikon / wordCount) * 100;
        return resultInPercent;
    }
}
