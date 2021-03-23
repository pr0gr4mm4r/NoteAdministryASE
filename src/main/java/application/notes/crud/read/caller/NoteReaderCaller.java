package application.notes.crud.read.caller;

import application.notes.crud.read.single.NoteReader;
import application.start.model.Caller;
import application.start.model.Interactor;

import java.nio.file.Path;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static application.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;

public class NoteReaderCaller implements Caller, Interactor {

    private Path path;
    private String noteName;

    public NoteReaderCaller() {
        interact();
        path = createCompletePath(noteName, path_for_notes);
        call();
    }

    @Override
    public void call() {
        new NoteReader(path);
    }

    @Override
    public void interact() {
        System.out.println("Please provide a name for the note to read:");
        noteName = scanner.nextLine();
    }
}
