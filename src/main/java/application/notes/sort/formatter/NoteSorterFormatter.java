package application.notes.sort.formatter;

import application.notes.spellcheck.model.Result;

import java.util.List;
import java.util.Map.Entry;

public class NoteSorterFormatter {
    private String theme;

    public NoteSorterFormatter(String theme) {
        this.theme = theme;
    }

    public Result convertListToResult(final List<Entry<String, Integer>> sortingOverview){
        Result result = new Result("");
        result.insertLineBreak();
        for (final Entry<String, Integer> stringIntegerEntry : sortingOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer wordCount = stringIntegerEntry.getValue();
            result.add(noteName + " " + "contains " + wordCount + " " + theme + ".");
            result.insertLineBreak();
        }
        return result;
    }
}
