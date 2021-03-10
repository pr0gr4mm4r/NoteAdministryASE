package base.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static base.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class RhymesNoteSorterResultFormatter {
    public RhymesNoteSorterResultFormatter() {
    }

    public String formatList(List<Entry<String, Integer>> finalRhymeOverview){
        String result = "";
        result = insertLineBreak(result);
        for (Entry<String, Integer> stringIntegerEntry : finalRhymeOverview) {
            String noteName = stringIntegerEntry.getKey();
            Integer wordCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + wordCount + " rhymes.";
            result = insertLineBreak(result);
        }
        return result;
    }
}
