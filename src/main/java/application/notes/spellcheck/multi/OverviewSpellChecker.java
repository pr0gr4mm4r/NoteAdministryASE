package application.notes.spellcheck.multi;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import application.notes.spellcheck.model.Result;
import application.notes.spellcheck.model.WordExistenceMapList;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.io.IOException;
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

    public static OverviewSpellChecker initializeOverviewSpellChecker() throws NoFilesInDirectoryException, IOException {
        final OverviewSpellChecker overviewSpellChecker = new OverviewSpellChecker();
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        final List<String[]> wordListList = noteStack.getSeparatedWordListList();
        overviewSpellChecker.wordExistenceMapList = initializeWordExistenceMapList(wordListList);
        overviewSpellChecker.fillPercentageValueList(overviewSpellChecker.percentageValueList, wordListList);
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

    void openLogFileDialogue(final Result result) throws IOException {
        System.out.println("Do you want to save the output as a logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            final LogFileDeclarator logFileDeclarator = LogFileDeclarator.initializeLogFileDeclarator("Spellcheck All Notes");
            logFileDeclarator.declareLogFile(result.getStringRepresentation());
        }
    }


    protected Result format() {
        final Result result = overviewSpellCheckerResultFormatter.formatList(wordExistenceMapList, percentageValueList);
        return result;
    }
}
