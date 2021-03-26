package application.notes.processors.single;

import application.notes.crud.read.single.NoteReader;

import java.nio.file.Path;
import java.util.Arrays;

import static utility.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static application.notes.crud.read.single.NoteReader.readNoteForNoteProcessing;


public class Note {
    private Path completePath;
    private String noteName;
    private String[] wordList;
    private String[] lineList;
    private String content;
    private String noteForGraphicalProcessing;


    public static Note initializeNote(String noteName){
        final Note note = new Note();
        final NoteReader noteReader = new NoteReader();
        note.noteName = noteName;
        note.completePath = createCompletePath(noteName, path_for_notes);
        note.content = readNoteForNoteProcessing(note.completePath);
        note.noteForGraphicalProcessing = noteReader.readNote(note.completePath);
        note.lineList = createLineList(note.content);
        note.wordList = createWordList(note.content);
        note.wordList = removeEmptyLines(note.wordList);
        return note;
    }


    private static String[] createLineList(String note) {
        return note.split("\n");
    }

    public static String[] removeEmptyLines(String[] words) {
        return Arrays.stream(words).filter(word->!word.equals("")).toArray(String[]::new);
    }

    public static String[] createWordList(String string){
        return string.split(" ");
    }

    public static String getPath() {
        return path_for_notes;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String[] getWordList() {
        return wordList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Path getCompletePath() {
        return completePath;
    }

    public String[] getLineList() {
        return lineList;
    }

    public String getNoteForGraphicalProcessing() {
        return noteForGraphicalProcessing;
    }
}
