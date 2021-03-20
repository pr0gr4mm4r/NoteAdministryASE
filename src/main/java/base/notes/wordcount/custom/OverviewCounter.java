package base.notes.wordcount.custom;

import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.ArrayList;
import java.util.List;

public class OverviewCounter {
    NoteCounterRaw noteCounterRaw = new NoteCounterRaw();

    public OverviewCounter() {
        List<Long> lineCountList = noteCounterRaw.getLineCountList();
        List<Integer> wordCountList = noteCounterRaw.getWordCountList();
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        System.out.println(multiNoteProcessor.getNoteList());
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            System.out.println(
                    noteNames.get(i) + " contains " + lineCountList.get(i) +
                    " lines and " + wordCountList.get(i) + " words.");
        }
    }
}
