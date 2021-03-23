package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.sort.abstraction.Sorter;
import application.WordExistenceMap;
import application.notes.sort.formatter.RhymesNoteSorterResultFormatter;
import application.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static application.notes.spellcheck.raw.SpellCheckerRaw.filterNegatives;
import static application.notes.spellcheck.raw.SpellCheckerRaw.filterPositives;

public class RhymesNoteSorter implements Sorter {
    MultiNoteProcessor multiNoteProcessor;
    List<String[]> noteList;
    List<String> noteNames;
    Map<String, Integer> rhymeOverview;

    @Override
    public Map initialize() {
        initializeVariables();
        int counter;
        for (int i = 0; i < noteList.size(); i++) {
            counter = 0;
            String[] currentNote = noteList.get(i);
            String currentNotename = noteNames.get(i);
            List<String> wordsInLexicon = filterPositives(currentNote);
            List<String> wordsNotInLexicon = filterNegatives(currentNote);
            WordExistenceMap wordExistence = new WordExistenceMap();
            wordExistence.fill(wordsInLexicon, wordsNotInLexicon);
            List<Entry<String, Boolean>> wordsInLexiconEntries = wordExistence.discardNegatives();;
            increaseCounterForEachRhyme(wordsInLexiconEntries, counter);
            rhymeOverview.put(currentNotename, counter);
        }
        return rhymeOverview;
    }

    private int increaseCounterForEachRhyme(List<Entry<String, Boolean>> wordsInLexiconEntries, int counter) {
        final int size = wordsInLexiconEntries.size();
        for (int j = 0; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                String firstWord = wordsInLexiconEntries.get(j).getKey();
                String secondWord = wordsInLexiconEntries.get(k).getKey();
                if (RiTa.isRhyme(firstWord, secondWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private void initializeVariables() {
        multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        noteList = multiNoteProcessor.getWordListList();
        noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        rhymeOverview = new StringIntegerMap<>();
    }

    @Override
    public List sort(Map rhymeOverview) {
        Set<Entry<String, Integer>> rhymeOverviewSet = rhymeOverview.entrySet();
        List<Entry<String, Integer>> finalRhymeOverview = new ArrayList<>(rhymeOverviewSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalRhymeOverview.sort(valueComparator);
        return finalRhymeOverview;
    }

    @Override
    public String format(List finalRhymeOverview) {
        RhymesNoteSorterResultFormatter rhymesNoteSorterResultFormatter = new RhymesNoteSorterResultFormatter();
        String formattedResult = rhymesNoteSorterResultFormatter.formatList(finalRhymeOverview);
        return formattedResult;

    }

    @Override
    public void print(String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            new LogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Rhymes");
        }
    }
}
