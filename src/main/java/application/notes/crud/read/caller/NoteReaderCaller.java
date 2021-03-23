package application.notes.crud.read.caller;

import application.notes.crud.read.single.NoteReader;

import java.nio.file.Path;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;
import static application.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;

public class NoteReaderCaller {

    public NoteReaderCaller(){
        System.out.println("Please provide a name for the note to read:");
        String noteName = scanner.nextLine();
        Path path = createCompletePath(noteName, path_for_notes);
        new NoteReader(path);
    }
}
