package application.notes.find.caller;

import application.notes.find.single.SingleNoteWordFinder;
import application.start.model.Caller;
import application.start.model.Interactor;

import static config.Globals.scanner;

public class SingleNoteWordFinderCaller implements Caller, Interactor {
    private String keyword;
    private String nameOfNote;

    public SingleNoteWordFinderCaller() {
        interact();
        call();
    }

    @Override
    public void call() {
        new SingleNoteWordFinder(keyword, nameOfNote);
    }

    @Override
    public void interact() {
        System.out.println("type in a keyword to search:");
        keyword = scanner.nextLine();
        System.out.println("type in the name of the note to search:");
        nameOfNote = scanner.nextLine();
    }
}
