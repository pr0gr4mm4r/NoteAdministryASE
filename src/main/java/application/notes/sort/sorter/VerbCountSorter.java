package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.NoteSorter;
import application.notes.sort.formatter.NoteSorterFormatter;
import utility.strings.ConfirmationString;
import utility.formatting.StringRepresentation.Result;
import rita.RiTa;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static config.Globals.scanner;

public class VerbCountSorter implements NoteSorter {
    private NoteStack noteStack;
    private List<String[]> noteList;
    private List<String> nameList;
    private Map<String, Integer> verbCountMap;

    public VerbCountSorter(final NoteStack multiNoteProcessor) {
        this.noteStack = multiNoteProcessor;
    }

    @Override
    public Map initializeMapToSort() {
        initializeVariables();
        for (int i = 0; i < noteList.size(); i++) {
            int verbCounter = 0;
            for (int j = 0; j < noteList.get(i).length; j++) {
                if (RiTa.isVerb(noteList.get(i)[j])) {
                    verbCounter++;
                }
            }
            final String nameOfNote = nameList.get(i);
            verbCountMap.put(nameOfNote, verbCounter);
        }
        return verbCountMap;
    }

    private void initializeVariables() {
        noteList = noteStack.getSeparatedWordListList();
        nameList = new ArrayList<>(noteStack.getNoteNames());
        verbCountMap = new HashMap<>();
    }

    @Override
    public List sort(final Map mapToSort) {
        final Set<Entry<String, Integer>> verbCountSet = mapToSort.entrySet();
        final List<Entry<String, Integer>> finalVerbCountList = new ArrayList<>(verbCountSet);
        final Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalVerbCountList.sort(valueComparator);
        return finalVerbCountList;
    }

    @Override
    public Result format(final List resultOfSorting) {
        final NoteSorterFormatter noteSorterFormatter = new NoteSorterFormatter("verbs");
        final Result formattedResult = noteSorterFormatter.convertListToResult(resultOfSorting);
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
        final LogFileDeclarator logFileDeclarator = initializeLogFileDeclarator("Sorting Notes by Quantity of Verbs");
        logFileDeclarator.declareLogFile(formattedResult);
    }
}
