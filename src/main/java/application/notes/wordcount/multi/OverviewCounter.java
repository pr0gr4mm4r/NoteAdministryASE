package application.notes.wordcount.multi;

import application.notes.processors.multi.NoteStack;
import application.notes.wordcount.raw.NoteCounterRaw;

import java.util.ArrayList;
import java.util.List;

import static config.Globals.path_for_notes;

public class OverviewCounter {
    NoteCounterRaw noteCounterRaw = new NoteCounterRaw();

    public OverviewCounter() {
        List<Long> lineCountList = noteCounterRaw.getLineCountList();
        List<Integer> wordCountList = noteCounterRaw.getWordCountList();
        NoteStack noteStack = NoteStack.initializeStack(path_for_notes);
        System.out.println(noteStack.getNoteList());
        List<String> noteNames = new ArrayList<>(noteStack.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            System.out.println(
                    noteNames.get(i) + " contains " + lineCountList.get(i) +
                    " lines and " + wordCountList.get(i) + " words.");
        }
    }
}
