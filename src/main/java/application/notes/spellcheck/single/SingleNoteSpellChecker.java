package application.notes.spellcheck.single;

import application.notes.processors.single.Note;
import application.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter;
import application.notes.spellcheck.model.Result;
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
    private final SingleNoteSpellCheckerResultFormatter singleNoteSpellCheckerResultFormatter = new SingleNoteSpellCheckerResultFormatter();

    private SingleNoteSpellChecker() {

    }

    public static SingleNoteSpellChecker initializeSingleNoteSpellChecker(final String noteName) {
        final SingleNoteSpellChecker singleNoteSpellChecker = new SingleNoteSpellChecker();
        final Note note = initializeNote(noteName);
        final String[] wordList = note.getWordList();
        singleNoteSpellChecker.spellCheckerRaw = initializeSpellCheckerRaw(wordList);
        final List<String> wordsInLexicon = singleNoteSpellChecker.spellCheckerRaw.getWordsInLexicon();
        final List<String> wordsNotInLexicon = singleNoteSpellChecker.spellCheckerRaw.getWordsNotInLexicon();
        singleNoteSpellChecker.wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        final double wordCountOfNote = wordList.length;
        final double wordsInLexikon = singleNoteSpellChecker.spellCheckerRaw.countWordsPresentInLexicon(wordList);
        final double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
        String resultString = singleNoteSpellChecker.singleNoteSpellCheckerResultFormatter.format(singleNoteSpellChecker.wordExistence, percentageValue);
        final Result result = new Result(resultString);
        result.print();
        return singleNoteSpellChecker;
    }
}
