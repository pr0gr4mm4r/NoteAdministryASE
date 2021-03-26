package application.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static utility.formatting.BasicFormatter.insertLineBreak;

public class VerbCountSorterResultFormatter {
    private final String theme;

    public VerbCountSorterResultFormatter() {
        this.theme = "verbs";
    }

     public String formatList(final List<Entry<String, Integer>> verbCountOverview) {
        String result = "";
        result = insertLineBreak(result);
        for (final Entry<String, Integer> stringIntegerEntry : verbCountOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer verbCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + verbCount + " "  + theme + ".";
            result = insertLineBreak(result);
        }
        return result;
    }
}
