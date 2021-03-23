package application.notes.find.caller;

import application.notes.find.multi.OverviewWordFinder;
import application.notes.processors.multi.MultiNoteProcessor;
import application.start.model.Caller;
import application.start.model.Interactor;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class OverviewWordFinderCaller implements Caller, Interactor {
    private String keyword;
    private final MultiNoteProcessor multiNoteProcessor;

    public OverviewWordFinderCaller() {
        multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
    }
    @Override
    public void call() {
        new OverviewWordFinder(keyword, multiNoteProcessor);
    }

    @Override
    public void interact() {
        System.out.println("type in a keyword to search:");
        keyword = scanner.nextLine();
    }
}
