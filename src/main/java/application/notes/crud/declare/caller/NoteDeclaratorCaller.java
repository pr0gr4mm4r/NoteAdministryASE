package application.notes.crud.declare.caller;

import application.notes.crud.declare.single.NoteDeclarator;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import java.nio.file.Path;
import java.nio.file.Paths;


import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteDeclaratorCaller implements Interactor, Caller {
    private String noteName;
    private Path pathToNote;

    public NoteDeclaratorCaller() {
        interact();
        pathToNote = createCompletePath(noteName, path_for_notes);
        call();
    }

    public static Path createCompletePath(String noteName, String path) {
        return Paths.get(path + noteName);
    }

    @Override
    public void call() {
        new NoteDeclarator(pathToNote, noteName);
    }

    @Override
    public void interact() {
        System.out.println("Please provide a name for the note:");
        noteName = scanner.nextLine();
    }
}
