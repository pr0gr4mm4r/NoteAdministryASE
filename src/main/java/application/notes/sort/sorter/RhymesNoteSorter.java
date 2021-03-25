package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.formatter.RhymesNoteSorterResultFormatter;
import application.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static config.Globals.scanner;
import static application.notes.spellcheck.raw.SpellCheckerRaw.filterPositives;

public class RhymesNoteSorter implements Sorter {
    NoteStack multiNoteProcessor;
    List<String[]> noteList = new ArrayList<>();
    List<String> noteNames = new ArrayList<>();
    Map<String, Integer> rhymeOverview = new StringIntegerMap();
    public RhymesNoteSorter(NoteStack multiNoteProcessor) {
        this.multiNoteProcessor = multiNoteProcessor;
    }

    @Override
    public Map<String, Integer> initialize() {
        initializeVariables();
        int counter;
        for (int i = 0; i < noteList.size(); i++) {
            counter = 0;
            String[] currentNote = noteList.get(i);
            String currentNotename = noteNames.get(i);
            List<String> wordsInLexicon = filterPositives(currentNote);
            counter = increaseCounterForEachRhyme(wordsInLexicon, counter);
            rhymeOverview.put(currentNotename, counter);
        }
        return rhymeOverview;
    }

    public int increaseCounterForEachRhyme(List<String> wordsInLexicon, int counter) {
        final int size = wordsInLexicon.size();
        for (int j = 0; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                String firstWord = wordsInLexicon.get(j);
                String secondWord = wordsInLexicon.get(k);
                if (RiTa.isRhyme(firstWord, secondWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private void initializeVariables() {
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
           logFileConfirmation(formattedResult);
        }
    }

    @Override
    public void logFileConfirmation(String formattedResult) {
        new LogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Rhymes");
    }
}
