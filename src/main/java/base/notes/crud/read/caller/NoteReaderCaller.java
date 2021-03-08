package base.notes.crud.read.caller;

import base.notes.crud.read.single.NoteReader;

import java.nio.file.Path;

import static base.config.Globals.scanner;
import static base.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;

public class NoteReaderCaller {

    public NoteReaderCaller(){
        System.out.println("Please provide a name for the note to read:");
        String noteName = scanner.nextLine();
        Path path = createCompletePath(noteName);
        new NoteReader(path);
    }
}
