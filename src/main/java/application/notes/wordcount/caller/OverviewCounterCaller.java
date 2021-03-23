package application.notes.wordcount.caller;

import application.notes.wordcount.multi.OverviewCounter;
import application.start.model.Caller;

import static config.Globals.path_for_notes;

public class OverviewCounterCaller implements Caller {
    public OverviewCounterCaller() {
        System.out.println("In the following there will be counted the words of all notes in " + path_for_notes);
        call();
    }

    @Override
    public void call() {
        new OverviewCounter();
    }
}
