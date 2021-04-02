package application.notes.processors.single;

import application.notes.crud.read.single.NoteReader;

import java.nio.file.Path;
import java.util.Arrays;

import static utility.formatting.WordListCreator.createWordList;
import static config.Globals.path_for_notes;
import static utility.path.PathCreator.createCompletePath;


public class Note {
    protected Path completePath;
    protected String noteName;
    protected String[] wordList;
    protected String[] lineList;
    protected String content;
    protected String contentForGraphicalProcessing;

    protected Note() {
    }

    public static Note initializeNote(final String noteName){
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

    String[] createLineList(final String note) {
        return note.split("\n");
    }

    public String[] removeEmptyLines(final String... words) {
        return Arrays.stream(words).filter(word->!word.equals("")).toArray(String[]::new);
    }

    public String getPath() {
        return path_for_notes;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(final String noteName) {
        this.noteName = noteName;
    }

    public String[] getWordList() {
        return wordList;
    }

    public Path getCompletePath() {
        return completePath;
    }

    public String[] getLineList() {
        return lineList;
    }

    public String getContentForGraphicalProcessing() {
        return contentForGraphicalProcessing;
    }

    public String getContent() {
        return content;
    }
}
