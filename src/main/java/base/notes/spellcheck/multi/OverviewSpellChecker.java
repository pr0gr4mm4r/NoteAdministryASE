package base.notes.spellcheck.multi;

import base.logfiles.crud.declare.single.LogFileDeclarator;
import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.spellcheck.formatter.OverviewSpellCheckerResultFormatter;
import base.notes.spellcheck.model.WordExistenceMapList;
import base.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.*;

import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;
import static base.notes.spellcheck.single.SingleNoteSpellChecker.calculatePercentageWiseOccurrence;


public class OverviewSpellChecker {
    private final WordExistenceMapList wordExistenceMapList = new WordExistenceMapList();
    private final OverviewSpellCheckerResultFormatter overviewSpellCheckerResultFormatter = new OverviewSpellCheckerResultFormatter();


    public OverviewSpellChecker() {
        final MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
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
        System.out.println(result);
        System.out.println();
        System.out.println("Do you want to save the output as a logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if(confirmation.equals("yes")){
            new LogFileDeclarator(result, "Spellcheck All Notes");
        }
    }

    public WordExistenceMapList getWordExistenceMapList() {
        return wordExistenceMapList;
    }

    public OverviewSpellCheckerResultFormatter getOverviewSpellCheckerResultFormatter() {
        return overviewSpellCheckerResultFormatter;
    }
}
