package application.notes.sort.formatter;

import application.notes.spellcheck.model.Result;

import java.util.List;
import java.util.Map.Entry;



public class RhymesNoteSorterResultFormatter {
    private final String theme;

    public RhymesNoteSorterResultFormatter() {
        this.theme = "rhymes";
    }

    public Result convertListToResult(final List<Entry<String, Integer>> finalRhymeOverview){
        Result result = new Result("");
        result.insertLineBreak();
        for (final Entry<String, Integer> stringIntegerEntry : finalRhymeOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer wordCount = stringIntegerEntry.getValue();
            result.add(noteName + " " + "contains " + wordCount + " " + theme + ".");
            result.insertLineBreak();
        }
        return result;
    }
}
