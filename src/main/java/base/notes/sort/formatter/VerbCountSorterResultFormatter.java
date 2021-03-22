package base.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static base.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class VerbCountSorterResultFormatter {
    public VerbCountSorterResultFormatter() {
    }

    public String formatList(List<Entry<String, Integer>> verbCountOverview) {
        String result = "";
        result = insertLineBreak(result);
        for (Entry<String, Integer> stringIntegerEntry : verbCountOverview) {
            String noteName = stringIntegerEntry.getKey();
            Integer verbCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + verbCount + " verbs.";
            result = insertLineBreak(result);
        }
        return result;
    }
}
