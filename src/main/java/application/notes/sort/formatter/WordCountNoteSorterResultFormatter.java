package application.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static utility.formatting.BasicFormatter.insertLineBreak;


public class WordCountNoteSorterResultFormatter {
    private final String theme;
    public WordCountNoteSorterResultFormatter() {
        this.theme = "words";
    }

    public String formatList(final List<Entry<String, Integer>> wordCountOverview) {
        String result = "";
        result = insertLineBreak(result);
        for (final Entry<String, Integer> stringIntegerEntry : wordCountOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer wordCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + wordCount + " " + theme + ".";
            result = insertLineBreak(result);
        }
        return result;
    }
}
