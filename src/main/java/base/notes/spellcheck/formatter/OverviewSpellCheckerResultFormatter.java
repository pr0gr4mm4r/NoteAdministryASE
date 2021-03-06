package base.notes.spellcheck.formatter;

import base.notes.spellcheck.model.WordExistenceMapList;

import java.util.List;

import static base.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class OverviewSpellCheckerResultFormatter {   //InterfacePotential
    SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    public OverviewSpellCheckerResultFormatter() {
    }

    public String formatList(WordExistenceMapList wordExistenceMapList, List<Double> percentageValues) {
        String result = "";
        for (int i = 0; i < wordExistenceMapList.size(); i++) {
            result = insertLineBreak(result);
            result += singleNoteSpellCheckerResultFormatter.format(wordExistenceMapList.get(i), percentageValues.get(i));
        }
        return result;
    }
}
