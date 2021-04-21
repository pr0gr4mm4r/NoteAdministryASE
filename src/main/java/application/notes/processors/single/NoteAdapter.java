package application.notes.processors.single;

import application.notes.crud.read.single.NoteReader;

import static config.Globals.path_for_notes;
import static utility.formatting.WordListCreator.createWordList;
import static utility.path.PathCreator.createCompletePath;

public class NoteAdapter {

    protected Note convertToNote(String noteName){
        final Note note = new Note();
        final NoteReader noteReader = new NoteReader();
        note.noteName = noteName;
        note.completePath = createCompletePath(noteName, path_for_notes);
        note.content = noteReader.readNoteForNoteProcessing(note.completePath);
        note.contentForGraphicalProcessing = noteReader.readNote(note.completePath);
        note.lineList = note.createLineList(note.content);
        note.wordList = createWordList(note.content);
        note.wordList = note.removeEmptyLines(note.wordList);
        return note;
    }
}
