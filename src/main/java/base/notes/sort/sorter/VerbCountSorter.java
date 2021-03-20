package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.logfiles.crud.declare.single.LogFileDeclarator;
import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.sort.formatter.WordCountNoteSorterResultFormatter;
import base.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;

import static base.config.Globals.scanner;

public class VerbCountSorter implements Sorter {
    @Override
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String[]> noteList = multiNoteProcessor.getWordListList();
        List<String> nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
        StringIntegerMap<String, Integer> verbCountMap = new StringIntegerMap<>();
        for (int i = 0; i < noteList.size(); i++) {
            int verbCounter = 0;
            for(int j = 0; j < noteList.get(i).length; j++){
                if(RiTa.isVerb(noteList.get(i)[j])){
                    verbCounter++;
                }
            }
            String nameOfNote = nameList.get(i);
            verbCountMap.put(nameOfNote, verbCounter);
        }
        Set<Entry<String, Integer>> verbCountSet = verbCountMap.entrySet();
        List<Entry<String, Integer>> finalVerbCountList = new ArrayList<>(verbCountSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalVerbCountList.sort(valueComparator);
        WordCountNoteSorterResultFormatter wordCountNoteSorterResultFormatter = new WordCountNoteSorterResultFormatter();
        String result = wordCountNoteSorterResultFormatter.formatList(finalVerbCountList);
        System.out.println(result);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if(confirmation.equals("yes")){
            new LogFileDeclarator(result, "Sorting Notes by Quantity of Verbs");
        }
        return null;
    }
}
