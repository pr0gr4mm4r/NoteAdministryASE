package application.notes.spellcheck.formatter;

import application.WordExistenceMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleNoteSpellCheckerResultFormatter {
    public SingleNoteSpellCheckerResultFormatter() {

    }

    public String format(WordExistenceMap wordExistenceMap, double percentageValue) {
        List<String> positives = wordExistenceMap.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
        List<String> negatives = wordExistenceMap.entrySet().stream().filter(word -> !word.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        String result = "CORRECTLY SPELLED:";
        result = insertLineBreak(result);
        for (String positive : positives) {
            result += positive;
            result = insertSpace(result);
        }
        result = insertLineBreak(insertLineBreak(result));
        result += "POTENTIALLY INCORRECTLY SPELLED:";
        result = insertLineBreak(result);
        for (String negative : negatives) {
            result += negative;
            result = insertSpace(result);
        }
        result = insertLineBreak(insertLineBreak(result));
        result += percentageValue + "% of words can be found in RiTa Dictionary";
        result = insertLineBreak(result);
        return result;
    }

    public static String insertLineBreak(String string){
        return string + "\n";
    }

    private String insertSpace(String string){
        return string + " ";
    }
}
