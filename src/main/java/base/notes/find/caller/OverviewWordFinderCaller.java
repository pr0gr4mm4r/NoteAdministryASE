package base.notes.find.caller;

import base.notes.find.multi.OverviewWordFinder;
import base.notes.processors.multi.MultiNoteProcessor;

import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;

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
