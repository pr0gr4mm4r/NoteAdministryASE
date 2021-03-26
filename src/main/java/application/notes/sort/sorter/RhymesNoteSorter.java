package application.notes.sort.sorter;

import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.formatter.RhymesNoteSorterResultFormatter;
import application.notes.sort.model.maps.StringIntegerMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static config.Globals.scanner;

public class RhymesNoteSorter implements Sorter {
    NoteStack noteStack;
    List<String[]> noteList = new ArrayList<>();
    List<String> noteNames = new ArrayList<>();
    Map<String, Integer> rhymeOverview = new StringIntegerMap();

    public RhymesNoteSorter(NoteStack multiNoteProcessor) {
        this.noteStack = multiNoteProcessor;
    }

    @Override
    public Map<String, Integer> initialize() {
        initializeVariables();
        int counter;
        for (int i = 0; i < noteList.size(); i++) {
            counter = 0;
            String[] currentNote = noteList.get(i);
            String currentNotename = noteNames.get(i);
            SpellCheckerRaw spellCheckerRaw = SpellCheckerRaw.initializeSpellCheckerRaw(currentNote);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
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
        noteList = noteStack.getWordListList();
        noteNames = new ArrayList<>(noteStack.getNoteNames());
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
        String formattedResult = rhymesNoteSorterResultFormatter.convertListToResultString(finalRhymeOverview);
        return formattedResult;

    }

    @Override
    public void dialogue(String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            createLogFile(formattedResult);
        }
    }

    @Override
    public void createLogFile(String formattedResult) {
        initializeLogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Rhymes");
    }
}
