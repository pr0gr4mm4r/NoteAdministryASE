package application.notes.crud.declare.caller;

import application.notes.crud.declare.single.NoteDeclarator;

import java.nio.file.Path;
import java.nio.file.Paths;


import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static application.start.NoteAdministryStart.programRun;

public class NoteDeclaratorCaller {
    private final String noteName;
    public NoteDeclaratorCaller() {
        if (programRun) {
            System.out.println("Please provide a name for the note:");
        }
        this.noteName = scanner.nextLine();
        final Path pathToNote = createCompletePath(noteName, path_for_notes);
        new NoteDeclarator(pathToNote, noteName);
    }

    public static Path createCompletePath(String noteName, String path) {
        return Paths.get(path + noteName);
    }

}
