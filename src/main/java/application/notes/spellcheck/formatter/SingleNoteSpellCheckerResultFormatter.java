package application.notes.spellcheck.formatter;

import application.notes.spellcheck.model.Result;
import utility.map.WordExistenceMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SingleNoteSpellCheckerResultFormatter {
    public SingleNoteSpellCheckerResultFormatter() {

    }

    public Result format(final WordExistenceMap wordExistenceMap, final double percentageValue) {
        final List<String> positives = wordExistenceMap.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
        final List<String> negatives = wordExistenceMap.entrySet().stream().filter(word -> !word.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        Result result = new Result("CORRECTLY SPELLED:");
        result.insertLineBreak();
        for (final String positive : positives) {
            result.add(positive);
            result.insertSpace();
        }
        result.insertLineBreak();
        result.insertLineBreak();
        result.add("POTENTIALLY INCORRECTLY SPELLED:");
        result.insertLineBreak();
        for (final String negative : negatives) {
            result.add(negative);
            result.insertSpace();
        }
        result.insertLineBreak();
        result.insertLineBreak();
        result.add(percentageValue + "% of words can be found in RiTa Dictionary");
        result.insertLineBreak();
        return result;
    }

}
