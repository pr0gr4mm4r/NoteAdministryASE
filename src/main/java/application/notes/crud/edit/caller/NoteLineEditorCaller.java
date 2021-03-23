package application.notes.crud.edit.caller;


import application.notes.crud.edit.single.NoteLineEditor;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import java.nio.file.Path;

import static config.Globals.*;
import static application.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;

public class NoteLineEditorCaller implements Caller, Interactor {
    private Path completePath;
    private String fileName;

    public NoteLineEditorCaller() {
        interact();
        completePath = createCompletePath(fileName, path_for_notes);
        call();
    }

    @Override
    public void interact() {
        System.out.println("Which note do you want to change?");
        fileName = scanner.nextLine();
    }

    @Override
    public void call() {
        new NoteLineEditor(completePath, fileName);
    }
}
