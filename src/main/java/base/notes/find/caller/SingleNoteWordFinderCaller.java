package base.notes.find.caller;

import base.notes.find.single.SingleNoteWordFinder;

import static base.config.Globals.scanner;

public class SingleNoteWordFinderCaller {
    public SingleNoteWordFinderCaller() {
        System.out.println("type in a keyword to search:");
        final String keyword = scanner.nextLine();
        System.out.println("type in the name of the note to search:");
        final String nameOfNote = scanner.nextLine();
        new SingleNoteWordFinder(keyword, nameOfNote);
    }
}
