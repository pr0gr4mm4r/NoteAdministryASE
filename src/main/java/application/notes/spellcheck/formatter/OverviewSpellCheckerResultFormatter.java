package application.notes.spellcheck.formatter;

import application.notes.spellcheck.model.WordExistenceMapList;

import java.util.List;

import static utility.formatting.BasicFormatter.insertLineBreak;

public class OverviewSpellCheckerResultFormatter  {
    SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    public OverviewSpellCheckerResultFormatter() {
    }

    public String formatList(final WordExistenceMapList wordExistenceMapList, final List<Double> percentageValues) {
        String result = "";
        for (int i = 0; i < wordExistenceMapList.size(); i++) {
            result = insertLineBreak(result);
            result += singleNoteSpellCheckerResultFormatter.format(wordExistenceMapList.get(i), percentageValues.get(i));
        }
        return result;
    }
}
