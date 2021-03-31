package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.NoteSorter;
import utility.confirmationString.ConfirmationString;
import application.notes.sort.formatter.RhymesNoteSorterResultFormatter;
import application.notes.spellcheck.model.Result;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import rita.RiTa;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static config.Globals.scanner;

public class RhymesNoteSorter implements NoteSorter {
    private final NoteStack noteStack;
    private List<String[]> noteList = new ArrayList<>();
    private List<String> noteNames = new ArrayList<>();
    private Map<String, Integer> rhymeOverview = new HashMap<>();

    public RhymesNoteSorter(final NoteStack noteStack) {
        this.noteStack = noteStack;
    }

    @Override
    public Map<String, Integer> initializeMapToSort() {
        initializeVariables();
        int counter;
        for (int i = 0; i < noteList.size(); i++) {
            counter = 0;
            final String[] currentNote = noteList.get(i);
            final String currentNotename = noteNames.get(i);
            final SpellCheckerRaw spellCheckerRaw = SpellCheckerRaw.initializeSpellCheckerRaw(currentNote);
            final List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            counter = increaseCounterForEachRhyme(wordsInLexicon, counter);
            rhymeOverview.put(currentNotename, counter);
        }
        return rhymeOverview;
    }

    public int increaseCounterForEachRhyme(final List<String> wordsInLexicon, int counter) {
        final int size = wordsInLexicon.size();
        for (int j = 0; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                final String firstWord = wordsInLexicon.get(j);
                final String secondWord = wordsInLexicon.get(k);
                if (RiTa.isRhyme(firstWord, secondWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private void initializeVariables() {
        noteList = noteStack.getSeparatedWordListList();
        noteNames = new ArrayList<>(noteStack.getNoteNames());
        rhymeOverview = new HashMap<>();
    }

    @Override
    public List sort(final Map mapToSort) {
        final Set<Entry<String, Integer>> rhymeOverviewSet = mapToSort.entrySet();
        List<Entry<String, Integer>> finalRhymeOverview = new ArrayList<>(rhymeOverviewSet);
        final Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalRhymeOverview.sort(valueComparator);
        return finalRhymeOverview;
    }

    @Override
    public Result format(final List resultOfSorting) {
        final RhymesNoteSorterResultFormatter rhymesNoteSorterResultFormatter = new RhymesNoteSorterResultFormatter();
        final String formattedResult = rhymesNoteSorterResultFormatter.convertListToResultString(resultOfSorting);
        return new Result(formattedResult);

    }

    @Override
    public void dialogue(final String formattedResultOfSorting) throws IOException {
        System.out.println(formattedResultOfSorting);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        final ConfirmationString confirmationString = new ConfirmationString(scanner.nextLine());
        if(confirmationString.confirm()){
            createLogFile(formattedResultOfSorting);
        }
    }

    public void createLogFile(final String formattedResult) throws IOException {
        final LogFileDeclarator logFileDeclarator = initializeLogFileDeclarator("Sorting Notes by Quantity of Rhymes");
        logFileDeclarator.declareLogFile(formattedResult);
    }
}
