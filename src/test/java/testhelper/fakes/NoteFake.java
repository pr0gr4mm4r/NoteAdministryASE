package testhelper.fakes;

import application.notes.crud.read.single.NoteReader;
import application.notes.processors.single.Note;

import static utility.formatting.WordListCreator.createWordList;
import static utility.path.PathCreator.createCompletePath;

public class NoteFake extends Note {
    public NoteFake() {
    }
    public static Note initializeNoteFake(final String noteName, final String customPath){
        final Note note = new Note();
        final NoteReader noteReader = new NoteReader();
        note.noteName = noteName;
        note.completePath = createCompletePath(noteName, customPath);
        note.content = noteReader.readNoteForNoteProcessing(note.completePath);
        note.wordList = createWordList(note.content);
        note.wordList = note.removeEmptyLines(note.wordList);
        return note;
    }
}
