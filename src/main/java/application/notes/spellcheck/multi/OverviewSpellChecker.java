package application.notes.spellcheck.multi;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import application.notes.spellcheck.model.WordExistenceMapList;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.*;

import static application.notes.processors.multi.NoteStack.*;
import static application.notes.spellcheck.model.WordExistenceMapList.initializeWordExistenceMapList;
import static application.notes.spellcheck.raw.SpellCheckerRaw.initializeSpellCheckerRaw;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static utility.calc.PercentageCalculator.calculatePercentageWiseOccurrence;


public class OverviewSpellChecker {
    private final OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter = new OverviewSpellCheckerResultFormatter();
    private final List<Double> percentageValueList = new ArrayList<>();
    private WordExistenceMapList wordExistenceMapList;

    private OverviewSpellChecker() {

    }

    public static OverviewSpellChecker initializeOverviewSpellChecker() throws NoFilesInDirectoryException {
        final OverviewSpellChecker overviewSpellChecker = new OverviewSpellChecker();
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        final List<String[]> wordListList = noteStack.getWordListList();
        overviewSpellChecker.wordExistenceMapList = initializeWordExistenceMapList(wordListList);
        overviewSpellChecker.fillPercentageValueList(overviewSpellChecker.percentageValueList, wordListList);
        final String formattedResult = overviewSpellChecker.format();
        overviewSpellChecker.print(formattedResult);
        overviewSpellChecker.openLogFileDialogue(formattedResult);
        return overviewSpellChecker;
    }

    private void fillPercentageValueList(final List<Double> percentageValueList, final List<String[]> wordListList) {
        for (final String[] wordList : wordListList) {
            final double wordCountOfNote = wordList.length;
            final SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(wordList);
            final double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
            final double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
            percentageValueList.add(percentageValue);
        }
    }

    private void openLogFileDialogue(final String result) {
        System.out.println("Do you want to save the output as a logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            final LogFileDeclarator logFileDeclarator = LogFileDeclarator.initializeLogFileDeclarator("Spellcheck All Notes");
            logFileDeclarator.declareLogFile(result);
        }
    }

    private void print(final String result) {
        System.out.println(result);
        System.out.println();
    }

    private String format() {
        return overviewSpellCheckerResultFormatter.formatList(wordExistenceMapList, percentageValueList);
    }
}
