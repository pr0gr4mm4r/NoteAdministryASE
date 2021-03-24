package application.notes.spellcheck.single;

import application.notes.processors.single.SingleNoteProcessor;
import application.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import application.WordExistenceMap;

import java.util.*;

public class SingleNoteSpellChecker {
    private final WordExistenceMap wordExistence;
    private final SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor;
    private SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    public SingleNoteSpellChecker(String noteName) {
        singleNoteProcessor = new SingleNoteProcessor(noteName);
        String[] wordList = singleNoteProcessor.getWordList();
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        wordExistence = new WordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        double wordCountOfNote = wordList.length;
        double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
        double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
        String result = singleNoteSpellCheckerResultFormatter.format(wordExistence, percentageValue);
        printResult(result);
    }

    private void printResult(String result) {
        System.out.println(result);
    }

    public static double calculatePercentageWiseOccurrence(double wordCount, double wordsInLexikon) {
        double resultInPercent = (wordsInLexikon / wordCount) * 100;
        return resultInPercent;
    }
}
