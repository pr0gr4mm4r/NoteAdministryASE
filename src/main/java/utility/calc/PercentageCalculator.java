package utility.calc;

public class PercentageCalculator {

    public PercentageCalculator() {
        
    }

    public static double calculatePercentageWiseOccurrence(final double actualCount, final double totalOccurrence) {
        final double resultInPercent = (totalOccurrence / actualCount) * 100;
        return resultInPercent;
    }
}
