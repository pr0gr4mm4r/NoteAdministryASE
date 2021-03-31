package utility.calc;

public class PercentageCalculator {

    public PercentageCalculator() { // Pure Fabrication wird verwendet, um mathematischen Sachverhalt vom Domänenwissen zu trennen
    }

    public static double calculatePercentageWiseOccurrence(final double actualCount, final double totalOccurrence) {
        final double resultInPercent = (totalOccurrence / actualCount) * 100;
        return resultInPercent;
    }
}
