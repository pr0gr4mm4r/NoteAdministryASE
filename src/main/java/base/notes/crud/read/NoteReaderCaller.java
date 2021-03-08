package base.notes.crud.read;

import java.nio.file.Path;

import static base.config.Globals.scanner;
import static base.notes.crud.declare.NoteDeclaratorCaller.createCompletePath;

public class NoteReaderCaller {

    public NoteReaderCaller(){
        System.out.println("Please provide a name for the note to read:");
        String noteName = scanner.nextLine();
        Path path = createCompletePath(noteName);
        new NoteReader(path);
    }
}
