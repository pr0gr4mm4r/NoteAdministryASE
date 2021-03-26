package application.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static utility.formatting.BasicFormatter.insertLineBreak;


public class RhymesNoteSorterResultFormatter {
    private final String theme;

    public RhymesNoteSorterResultFormatter() {
        this.theme = "rhymes";
    }

    public String convertListToResultString(final List<Entry<String, Integer>> finalRhymeOverview){
        String result = "";
        result = insertLineBreak(result);
        for (final Entry<String, Integer> stringIntegerEntry : finalRhymeOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer wordCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + wordCount + " " + theme + ".";
            result = insertLineBreak(result);
        }
        return result;
    }
}
