package application.notes.spellcheck.single;

import application.notes.processors.single.Note;
import application.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import utility.map.WordExistenceMap;

import java.util.*;

import static utility.calc.PercentageCalculator.calculatePercentageWiseOccurrence;
import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.processors.single.Note.initializeNote;
import static application.notes.spellcheck.raw.SpellCheckerRaw.initializeSpellCheckerRaw;

public class SingleNoteSpellChecker {
    private WordExistenceMap wordExistence;
    private SpellCheckerRaw spellCheckerRaw;
    private SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    private SingleNoteSpellChecker() {

    }

    public static SingleNoteSpellChecker initializeSingleNoteSpellChecker(String noteName) {
        SingleNoteSpellChecker singleNoteSpellChecker = new SingleNoteSpellChecker();
        Note note = initializeNote(noteName);
        String[] wordList = note.getWordList();
        singleNoteSpellChecker.spellCheckerRaw = initializeSpellCheckerRaw(wordList);
        List<String> wordsInLexicon = singleNoteSpellChecker.spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = singleNoteSpellChecker.spellCheckerRaw.getWordsNotInLexicon();
        singleNoteSpellChecker.wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        double wordCountOfNote = wordList.length;
        double wordsInLexikon = singleNoteSpellChecker.spellCheckerRaw.countWordsPresentInLexicon(wordList);
        double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
        String result = singleNoteSpellChecker.singleNoteSpellCheckerResultFormatter.format(singleNoteSpellChecker.wordExistence, percentageValue);
        singleNoteSpellChecker.printResult(result);
        return singleNoteSpellChecker;
    }

    private void printResult(String result) {
        System.out.println(result);
    }


}
