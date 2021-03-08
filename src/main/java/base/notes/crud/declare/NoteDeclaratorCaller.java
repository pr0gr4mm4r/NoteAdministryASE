package base.notes.crud.declare;

import java.nio.file.Path;
import java.nio.file.Paths;


import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;
import static base.start.NoteAdministryStart.programRun;

public class NoteDeclaratorCaller {
    private String noteName;
    public NoteDeclaratorCaller() {
        if (programRun) {
            System.out.println("Please provide a name for the note:");
        }
        this.noteName = scanner.nextLine();
        final Path pathToNote = createCompletePath(noteName);
        new NoteDeclarator(pathToNote, noteName);
    }

    public static Path createCompletePath(String noteName) {
        return Paths.get(path_for_notes + noteName);
    }

}