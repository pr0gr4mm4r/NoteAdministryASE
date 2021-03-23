package application.notes.find.caller;

import application.notes.find.multi.OverviewWordFinder;
import application.notes.processors.multi.MultiNoteProcessor;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class OverviewWordFinderCaller {
    private final String keyword;
    public OverviewWordFinderCaller() {
        System.out.println("type in a keyword to search:");
        keyword = scanner.nextLine();
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        new OverviewWordFinder(keyword, multiNoteProcessor);
    }

    public String getKeyword() {
        return keyword;
    }
}
