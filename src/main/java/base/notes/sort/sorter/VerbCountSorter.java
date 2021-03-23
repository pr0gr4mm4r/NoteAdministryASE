package base.notes.sort.sorter;

import base.logfiles.crud.declare.single.LogFileDeclarator;
import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.sort.abstraction.Sorter;
import base.notes.sort.formatter.VerbCountSorterResultFormatter;
import base.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;

public class VerbCountSorter implements Sorter {
    MultiNoteProcessor multiNoteProcessor;
    List<String[]> noteList;
    List<String> nameList;
    StringIntegerMap<String, Integer> verbCountMap;

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
        multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        noteList = multiNoteProcessor.getWordListList();
        nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
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
    public void print(String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            new LogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Verbs");
        }
    }
}
