package application.notes.wordcount.multi;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.wordcount.raw.NoteCounterRaw;

import java.util.ArrayList;
import java.util.List;

import static application.notes.wordcount.raw.NoteCounterRaw.initializeNoteCounterRaw;
import static config.Globals.path_for_notes;

public class OverviewCounter {
    NoteCounterRaw noteCounterRaw;
    List<Long> lineCountList;
    List<Integer> wordCountList;
    List<String> noteNames;
    NoteStack noteStack;

    public OverviewCounter() {

    }

    public static OverviewCounter initializeOverviewCounter() throws NoFilesInDirectoryException {
        final OverviewCounter overviewCounter = new OverviewCounter();
        overviewCounter.noteCounterRaw = initializeNoteCounterRaw();
        overviewCounter.lineCountList = overviewCounter.noteCounterRaw.getLineCountList();
        overviewCounter.wordCountList = overviewCounter.noteCounterRaw.getWordCountList();
        overviewCounter.noteStack = NoteStack.initializeNoteStack(path_for_notes);
        overviewCounter.noteNames = new ArrayList<>(overviewCounter.noteStack.getNoteNames());
        return overviewCounter;
    }

    public void printResults() {
        for (int i = 0; i < this.noteNames.size(); i++) {
            System.out.println(
                    this.noteNames.get(i) + " contains " + this.lineCountList.get(i) +
                            " lines and " + this.wordCountList.get(i) + " words.");
        }
    }
}
