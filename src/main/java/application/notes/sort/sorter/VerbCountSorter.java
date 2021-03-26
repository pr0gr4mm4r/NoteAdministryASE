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

    public VerbCountSorter(NoteStack multiNoteProcessor) {
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
            String nameOfNote = nameList.get(i);
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
    public List sort(Map verbCountMap) {
        Set<Entry<String, Integer>> verbCountSet = verbCountMap.entrySet();
        List<Entry<String, Integer>> finalVerbCountList = new ArrayList<>(verbCountSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalVerbCountList.sort(valueComparator);
        return finalVerbCountList;
    }

    @Override
    public String format(List finalVerbCountList) {
        VerbCountSorterResultFormatter verbCountSorterResultFormatter = new VerbCountSorterResultFormatter();
        String result = verbCountSorterResultFormatter.formatList(finalVerbCountList);
        return result;
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
        LogFileDeclarator logFileDeclarator = initializeLogFileDeclarator("Sorting Notes by Quantity of Verbs");
        logFileDeclarator.declareLogFile(formattedResult);
    }
}
