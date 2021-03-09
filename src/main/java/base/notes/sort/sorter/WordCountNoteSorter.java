package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.logfiles.crud.declare.LogFileDeclarator;
import base.notes.processors.MultiNoteProcessor;
import base.notes.sort.formatter.WordCountNoteSorterResultFormatter;
import base.notes.sort.model.maps.StringIntegerMap;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.*;
import java.util.Map.Entry;

import static base.config.Globals.scanner;


public class WordCountNoteSorter implements Sorter {
    @Override
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        List<String> noteList = multiNoteProcessor.getNoteList();
        List<String> nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
        List<Integer> wordCountList = noteCounterRaw.getWordCountList();
        StringIntegerMap<String, Integer> wordCountMap = new StringIntegerMap<>();
        for (int i = 0; i < noteList.size(); i++) {
            String nameOfNote = nameList.get(i);
            Integer wordCount = wordCountList.get(i);
            wordCountMap.put(nameOfNote, wordCount);
        }
        Set wordCountSet = wordCountMap.entrySet();
        List finalWordCountList = new ArrayList(wordCountSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalWordCountList.sort(valueComparator);
        System.out.println(finalWordCountList);
        WordCountNoteSorterResultFormatter wordCountNoteSorterResultFormatter = new WordCountNoteSorterResultFormatter();
        String result = wordCountNoteSorterResultFormatter.formatList(finalWordCountList);
        System.out.println();
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if(confirmation.equals("yes")){
            new LogFileDeclarator(result, "Sort Notes by quantity of Rhymes");
        }
        return noteList.toString();
    }
}
