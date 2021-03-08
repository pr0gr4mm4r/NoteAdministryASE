package base.notes.crud.edit;


import java.nio.file.Path;

import static base.config.Globals.*;
import static base.notes.crud.declare.NoteDeclaratorCaller.createCompletePath;

public class NoteLineEditorCaller {
    public NoteLineEditorCaller(){
        System.out.println("Which note do you want to change?");
        String fileName = scanner.nextLine();
        final Path completePath = createCompletePath(fileName);
        new NoteLineEditor(completePath, fileName);
    }
}
