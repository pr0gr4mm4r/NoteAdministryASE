package application.notes.spellcheck.formatter;

import utility.map.WordExistenceMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utility.formatting.BasicFormatter.insertLineBreak;

public class SingleNoteSpellCheckerResultFormatter {
    public SingleNoteSpellCheckerResultFormatter() {

    }

    public String format(final WordExistenceMap wordExistenceMap, final double percentageValue) {
        final List<String> positives = wordExistenceMap.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
        final List<String> negatives = wordExistenceMap.entrySet().stream().filter(word -> !word.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        String result = "CORRECTLY SPELLED:";
        result = insertLineBreak(result);
        for (final String positive : positives) {
            result += positive;
            result = insertSpace(result);
        }
        result = insertLineBreak(insertLineBreak(result));
        result += "POTENTIALLY INCORRECTLY SPELLED:";
        result = insertLineBreak(result);
        for (final String negative : negatives) {
            result += negative;
            result = insertSpace(result);
        }
        result = insertLineBreak(insertLineBreak(result));
        result += percentageValue + "% of words can be found in RiTa Dictionary";
        result = insertLineBreak(result);
        return result;
    }

    private String insertSpace(final String string){
        return string + " ";
    }
}
