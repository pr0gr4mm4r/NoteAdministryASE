package testhelper.fakes;

import application.notes.crud.declare.single.NoteDeclarator;

import static utility.path.PathCreator.createCompletePath;

public class NoteDeclaratorFake extends NoteDeclarator {
    public NoteDeclaratorFake() {
    }

    public static NoteDeclaratorFake initializeFakeNoteDeclarator(final String noteName, final String path) {
        NoteDeclaratorFake noteDeclaratorFake = new NoteDeclaratorFake();
        noteDeclaratorFake.noteName = noteName;
        noteDeclaratorFake.pathToNote = createCompletePath(noteName, path);
        return noteDeclaratorFake;
    }

    public void printSuccessMessage() {

    }
}
