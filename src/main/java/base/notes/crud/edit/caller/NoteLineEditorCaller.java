package base.notes.crud.edit.caller;


import base.notes.crud.edit.single.NoteLineEditor;

import java.nio.file.Path;

import static base.config.Globals.*;
import static base.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;

public class NoteLineEditorCaller {
    public NoteLineEditorCaller(){
        System.out.println("Which note do you want to change?");
        String fileName = scanner.nextLine();
        final Path completePath = createCompletePath(fileName);
        new NoteLineEditor(completePath, fileName);
    }
}
