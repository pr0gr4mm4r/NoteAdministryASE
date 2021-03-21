package base.notes.spellcheck.single;

import base.notes.processors.single.SingleNoteProcessor;
import base.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.ui.WordExistenceMap;

import static base.config.Globals.scanner;

import java.util.*;

public class SingleNoteSpellChecker {
    private final WordExistenceMap wordExistenceMap = new WordExistenceMap();
    private final SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor;
    private SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    public SingleNoteSpellChecker() { //test one file, test all / write stats and which are wrong
        String noteName = scanner.nextLine();
        singleNoteProcessor = new SingleNoteProcessor(noteName);
        String[] wordList = singleNoteProcessor.getWordList();
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        wordExistenceMap.fill(wordsInLexicon, wordsNotInLexicon);

//auslagern
        double wordCountOfNote = wordList.length;
        double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
        double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
        String result = singleNoteSpellCheckerResultFormatter.format(wordExistenceMap, percentageValue);
        System.out.println(result);
    }

    public static double calculatePercentageWiseOccurrence(double wordCount, double wordsInLexikon) {
        double resultInPercent = (wordsInLexikon / wordCount) * 100;
        return resultInPercent;
    }
}
