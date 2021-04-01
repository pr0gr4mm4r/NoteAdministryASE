package application.notes.sort.formatter;

import application.notes.spellcheck.model.Result;

import java.util.List;
import java.util.Map.Entry;

public class VerbCountSorterResultFormatter {
    private final String theme;

    public VerbCountSorterResultFormatter() {
        this.theme = "verbs";
    }

    public Result formatList(final List<Entry<String, Integer>> verbCountOverview){
        Result result = new Result("");
        result.insertLineBreak();
        for (final Entry<String, Integer> stringIntegerEntry : verbCountOverview) {
            final String noteName = stringIntegerEntry.getKey();
            final Integer verbCount = stringIntegerEntry.getValue();
            result.add(noteName + " " + "contains " + verbCount + " " + theme + ".");
            result.insertLineBreak();
        }
        return result;
    }



}
