package application.notes.sort.formatter;

import java.util.List;
import java.util.Map.Entry;

import static application.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class WordCountNoteSorterResultFormatter {
    private final String theme;
    public WordCountNoteSorterResultFormatter() {
        this.theme = "words";
    }

    public String formatList(List<Entry<String, Integer>> wordCountOverview) {
        String result = "";
        result = insertLineBreak(result);
        for (Entry<String, Integer> stringIntegerEntry : wordCountOverview) {
            String noteName = stringIntegerEntry.getKey();
            Integer wordCount = stringIntegerEntry.getValue();
            result += noteName + " " + "contains " + wordCount + " " + theme + ".";
            result = insertLineBreak(result);
        }
        return result;
    }
}
