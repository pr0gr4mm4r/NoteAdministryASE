package application.notes.crud.read.caller;

import application.notes.crud.read.single.NoteReader;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import java.nio.file.Path;

import static application.redg.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

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
