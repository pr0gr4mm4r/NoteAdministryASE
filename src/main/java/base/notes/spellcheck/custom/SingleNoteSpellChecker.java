package base.notes.spellcheck.custom;

import base.notes.processors.SingleNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceMap;

import static base.config.Globals.scanner;

import java.util.*;

public class SingleNoteSpellChecker {
    private final WordExistenceMap wordExistenceMap = new WordExistenceMap();
    private final SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor;

    public SingleNoteSpellChecker() { //test one file, test all / write stats and which are wrong
        String noteName = scanner.nextLine();
        singleNoteProcessor = new SingleNoteProcessor(noteName);
        String[] wordList = singleNoteProcessor.getWordList();
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        wordExistenceMap.fill(wordsInLexicon, wordsNotInLexicon);
        System.out.println(wordExistenceMap);
//auslagern
        double wordCountOfNote = wordList.length;
        double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
        double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
        System.out.println(percentageValue + " % sind Wörter aus dem Lexikon");
    }

    public static double calculatePercentageWiseOccurrence(double wordCount, double wordsInLexikon) {
        double resultInPercent = (wordsInLexikon / wordCount) * 100;
        return resultInPercent;
    }
}
