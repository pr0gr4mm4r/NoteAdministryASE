package base.notes.sort;

import base.interfaces.Sorter;
import base.notes.processors.MultiNoteProcessor;
import base.notes.sort.model.WordCountMap;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.ArrayList;
import java.util.List;


public class WordCountNoteSorter implements Sorter {
    @Override
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        List<String> noteList = multiNoteProcessor.getNoteList();
        List<String> nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
        List<Integer> wordCountList = noteCounterRaw.getWordCountList();
        System.out.println(noteList);
        WordCountMap<String, Integer> wordCountMap = new WordCountMap();
        for (int i = 0; i < noteList.size(); i++) {
            String nameOfNote = nameList.get(i);
            Integer wordCount = wordCountList.get(i);
            wordCountMap.put(nameOfNote, wordCount);
        }

        System.out.println(wordCountMap);
        return noteList.toString();
    }
}
