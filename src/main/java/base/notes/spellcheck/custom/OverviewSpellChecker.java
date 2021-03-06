package base.notes.spellcheck.custom;

import base.notes.processors.MultiNoteProcessor;
import base.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import base.notes.spellcheck.model.WordExistenceMapList;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceMap;

import java.util.*;


public class OverviewSpellChecker {
    private final WordExistenceMapList wordExistenceMapList = new WordExistenceMapList();
    private final OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter = new OverviewSpellCheckerResultFormatter();


    public OverviewSpellChecker() {
        final MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        final List<String[]> wordListList = multiNoteProcessor.getWordListList();
        wordExistenceMapList.fill(wordListList);


        String result = overviewSpellCheckerResultFormatter.formatList(wordExistenceMapList);
        System.out.println(result);
    }

    public WordExistenceMapList getWordExistenceMapList() {
        return wordExistenceMapList;
    }
}
