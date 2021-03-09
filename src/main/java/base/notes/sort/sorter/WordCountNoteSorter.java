package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.notes.processors.MultiNoteProcessor;
import base.notes.sort.model.maps.StringIntegerMap;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.*;
import java.util.Map.Entry;


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
        return noteList.toString();
    }
}
