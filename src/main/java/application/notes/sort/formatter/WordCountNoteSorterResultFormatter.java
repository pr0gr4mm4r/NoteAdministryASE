package application.notes.sort.formatter;

import application.notes.spellcheck.model.Result;

import java.util.List;
import java.util.Map.Entry;



public class WordCountNoteSorterResultFormatter {
    private final String theme;
    public WordCountNoteSorterResultFormatter() {
        this.theme = "words";
    }

    public Result formatList(final List<Entry<String, Integer>> wordCountOverview) {
        Result result = new Result("");
        result.insertLineBreak();
        for (final Entry<String, Integer> stringIntegerEntry : wordCountOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer wordCount = stringIntegerEntry.getValue();
            result.add(noteName + " " + "contains " + wordCount + " " + theme + ".");
            result.insertLineBreak();
        }
        return result;
    }
}
