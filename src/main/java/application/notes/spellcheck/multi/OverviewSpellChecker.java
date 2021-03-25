package application.notes.spellcheck.multi;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoteStack;
import application.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import application.notes.spellcheck.model.WordExistenceMapList;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.*;

import static application.notes.processors.multi.NoteStack.*;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static application.notes.spellcheck.single.SingleNoteSpellChecker.calculatePercentageWiseOccurrence;


public class OverviewSpellChecker {
    private final WordExistenceMapList wordExistenceMapList = new WordExistenceMapList();
    private final OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter = new OverviewSpellCheckerResultFormatter();
    private final List<Double> percentageValueList = new ArrayList<>();

    public OverviewSpellChecker() {

    }

    public static OverviewSpellChecker initializeOverviewSpellChecker() {
        OverviewSpellChecker overviewSpellChecker = new OverviewSpellChecker();
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        final List<String[]> wordListList = noteStack.getWordListList();
        overviewSpellChecker.wordExistenceMapList.fill(wordListList);
        overviewSpellChecker.fillPercentageValueList(overviewSpellChecker.percentageValueList, wordListList);
        String formattedResult = overviewSpellChecker.format(overviewSpellChecker.overviewSpellCheckerResultFormatter, overviewSpellChecker.percentageValueList);
        overviewSpellChecker.print(formattedResult);
        overviewSpellChecker.openLogFileDialogue(formattedResult);
        return overviewSpellChecker;
    }

    private void fillPercentageValueList(List<Double> percentageValueList, List<String[]> wordListList) {
        for (String[] wordList : wordListList) {
            double wordCountOfNote = wordList.length;
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(wordList);
            double percentageValue = calculatePercentageWiseOccurrence(wordCountOfNote, wordsInLexikon);
            percentageValueList.add(percentageValue);
        }
    }

    private void openLogFileDialogue(String result) {
        System.out.println("Do you want to save the output as a logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            LogFileDeclarator.initializeLogFileDeclarator(result, "Spellcheck All Notes");
        }
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println();
    }

    private String format(OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter, List<Double> percentageValueList) {
        return overviewSpellCheckerResultFormatter.formatList(wordExistenceMapList, percentageValueList);
    }
}
