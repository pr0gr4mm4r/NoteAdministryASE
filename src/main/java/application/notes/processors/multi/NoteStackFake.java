package application.notes.processors.multi;

import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import application.notes.processors.single.Note;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static application.notes.processors.single.NoteFake.initializeNoteFake;

public class NoteStackFake extends NoteStack{
    private NoteStackFake() {

    }

    public static NoteStack initializeNoteStackFake(final String customPath) throws NoFilesInDirectoryException, IOException {
        final NoteStackFake noteStackFake = new NoteStackFake();
        noteStackFake.noteNames = noteStackFake.listNoteNames(customPath);
        if (noteStackFake.noteNames.isEmpty()) {
            throw new NoFilesInDirectoryException(customPath);
        }
        noteStackFake.notes = noteStackFake.initializeNotes(noteStackFake, customPath);
        noteStackFake.pathList = noteStackFake.createPathList(noteStackFake.notes);
        noteStackFake.noteContentList = noteStackFake.createNoteContentList(noteStackFake.notes);
        noteStackFake.separateWordsForEachNote(noteStackFake.notes, noteStackFake.separatedWordListList);
        return noteStackFake;
    }

    public List<Note> initializeNotes(final NoteStack noteStack, final String customPath) {
        final List<String> noteNames = new ArrayList<>(noteStack.noteNames);
        for (final String noteName : noteNames) {
            noteStack.notes.add(initializeNoteFake(noteName, customPath));
        }
        return noteStack.notes;
    }
}
