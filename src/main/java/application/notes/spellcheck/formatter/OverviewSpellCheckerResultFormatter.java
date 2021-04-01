package application.notes.spellcheck.formatter;

import application.notes.spellcheck.model.Result;
import application.notes.spellcheck.model.WordExistenceMapList;

import java.util.List;


public class OverviewSpellCheckerResultFormatter {
    SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    public OverviewSpellCheckerResultFormatter() {
    }

    public Result formatList(final WordExistenceMapList wordExistenceMapList, final List<Double> percentageValues) {
        Result result = new Result("");
        for (int i = 0; i < wordExistenceMapList.size(); i++) {
            result.insertLineBreak();
            result.add(singleNoteSpellCheckerResultFormatter.format(wordExistenceMapList.get(i), percentageValues.get(i)).getStringRepresentation());
        }
        return result;
    }
}
