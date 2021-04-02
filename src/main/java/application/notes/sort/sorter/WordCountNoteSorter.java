package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.NoteSorter;
import application.notes.sort.formatter.NoteSorterFormatter;
import utility.strings.ConfirmationString;
import utility.formatting.StringRepresentation.Result;
import application.notes.wordcount.raw.NoteCounterRaw;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static application.notes.wordcount.raw.NoteCounterRaw.initializeNoteCounterRaw;
import static config.Globals.scanner;


public class WordCountNoteSorter implements NoteSorter {
    private List<String> noteList;
    private List<String> nameList;
    private List<Integer> wordCountList;
    private NoteStack noteStack;
    private NoteCounterRaw noteCounterRaw;

    public WordCountNoteSorter(final NoteStack noteStack) {
        this.noteStack = noteStack;
    }

    @Override
    public Map initializeMapToSort() throws NoFilesInDirectoryException, IOException {
        initializeVariables();
        final Map<String, Integer> wordCountMap = new HashMap<>();
        for (int i = 0; i < noteList.size(); i++) {
            final String nameOfNote = nameList.get(i);
            final Integer wordCount = wordCountList.get(i);
            wordCountMap.put(nameOfNote, wordCount);
        }
        return wordCountMap;
    }

    private void initializeVariables() throws NoFilesInDirectoryException, IOException {
        noteCounterRaw = initializeNoteCounterRaw();
        noteList = noteStack.getNoteContentList();
        nameList = new ArrayList<>(noteStack.getNoteNames());
        wordCountList = noteCounterRaw.getWordCountList();
    }

    @Override
    public List sort(final Map mapToSort) {
        final Set<Entry<String, Integer>> wordCountSet = mapToSort.entrySet();
        final List<Entry<String, Integer>> finalWordCountList = new ArrayList<>(wordCountSet);
        final Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalWordCountList.sort(valueComparator);
        return finalWordCountList;
    }

    @Override
    public Result format(final List resultOfSorting) {
        final NoteSorterFormatter wordCountNoteSorterResultFormatter = new NoteSorterFormatter("wordCount");
        final Result formattedResult = wordCountNoteSorterResultFormatter.convertListToResult(resultOfSorting);
        return formattedResult;
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
