package base.notes.spellcheck.custom;

import base.logfiles.crud.declare.LogFileDeclarator;
import base.notes.processors.MultiNoteProcessor;
import base.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import base.notes.spellcheck.model.WordExistenceMapList;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceMap;

import java.util.*;

import static base.notes.spellcheck.custom.SingleNoteSpellChecker.calculatePercentageWiseOccurrence;


public class OverviewSpellChecker {
    private final WordExistenceMapList wordExistenceMapList = new WordExistenceMapList();
    private final OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter = new OverviewSpellCheckerResultFormatter();


    public OverviewSpellChecker() {
        final MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        final List<String[]> wordListList = multiNoteProcessor.getWordListList();
        wordExistenceMapList.fill(wordListList);

        List<Double> percentageValueList = new ArrayList<>();
        for (String[] wordList : wordListList) {
            double wordCountOfNote = wordList.length;
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
            double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
            percentageValueList.add(percentageValue);
        }
        String result = overviewSpellCheckerResultFormatter.formatList(wordExistenceMapList, percentageValueList);
        LogFileDeclarator logFileDeclarator = new LogFileDeclarator(result, "Spellcheck All Notes");
        System.out.println(result);
    }

    public WordExistenceMapList getWordExistenceMapList() {
        return wordExistenceMapList;
    }
}
