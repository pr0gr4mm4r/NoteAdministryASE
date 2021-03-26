package application.notes.sort.sorter;

import application.logfiles.crud.declare.single.LogFileDeclarator;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.formatter.VerbCountSorterResultFormatter;
import application.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static config.Globals.scanner;

public class VerbCountSorter implements Sorter {
    NoteStack noteStack;
    List<String[]> noteList;
    List<String> nameList;
    StringIntegerMap<String, Integer> verbCountMap;

    public VerbCountSorter(final NoteStack multiNoteProcessor) {
        this.noteStack = multiNoteProcessor;
    }

    @Override
    public Map initialize() {
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
        noteList = noteStack.getWordListList();
        nameList = new ArrayList<>(noteStack.getNoteNames());
        verbCountMap = new StringIntegerMap<>();
    }

    @Override
    public List sort(final Map verbCountMap) {
        final Set<Entry<String, Integer>> verbCountSet = verbCountMap.entrySet();
        final List<Entry<String, Integer>> finalVerbCountList = new ArrayList<>(verbCountSet);
        final Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalVerbCountList.sort(valueComparator);
        return finalVerbCountList;
    }

    @Override
    public String format(final List finalVerbCountList) {
        final VerbCountSorterResultFormatter verbCountSorterResultFormatter = new VerbCountSorterResultFormatter();
        final String result = verbCountSorterResultFormatter.formatList(finalVerbCountList);
        return result;
    }

    @Override
    public void dialogue(final String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        final String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            createLogFile(formattedResult);
        }
    }

    @Override
    public void createLogFile(final String formattedResult) {
        final LogFileDeclarator logFileDeclarator = initializeLogFileDeclarator("Sorting Notes by Quantity of Verbs");
        logFileDeclarator.declareLogFile(formattedResult);
    }
}
