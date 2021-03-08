package base.notes.sort;

import base.interfaces.Sorter;
import base.notes.processors.MultiNoteProcessor;
import base.notes.sort.model.WordCountMap;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class WordCountNoteSorter implements Sorter {
    @Override
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        List<String> noteList = multiNoteProcessor.getNoteList();
        List<String> nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
        List<Integer> wordCountList = noteCounterRaw.getWordCountList();
        WordCountMap wordCountMap = new WordCountMap();
        for (int i = 0; i < noteList.size(); i++) {
            String nameOfNote = nameList.get(i);
            Integer wordCount = wordCountList.get(i);
            wordCountMap.put(nameOfNote, wordCount);
        }
        Set wordCountSet = wordCountMap.entrySet();
        List wordCountList2 = new ArrayList(wordCountSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        wordCountList2.sort(valueComparator);
        System.out.println(wordCountList2);
        return noteList.toString();
    }
}
