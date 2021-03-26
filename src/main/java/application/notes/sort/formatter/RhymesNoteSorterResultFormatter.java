package application.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static application.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class RhymesNoteSorterResultFormatter {
    private final String theme;

    public RhymesNoteSorterResultFormatter() {
        this.theme = "rhymes";
    }

    public String convertListToResultString(List<Entry<String, Integer>> finalRhymeOverview){
        String result = "";
        result = insertLineBreak(result);
        for (Entry<String, Integer> stringIntegerEntry : finalRhymeOverview) {
            String noteName = stringIntegerEntry.getKey();
            Integer wordCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + wordCount + " " + theme + ".";
            result = insertLineBreak(result);
        }
        return result;
    }
}
