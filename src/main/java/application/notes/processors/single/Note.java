package application.notes.processors.single;

import application.notes.crud.read.single.NoteReader;

import java.nio.file.Path;
import java.util.Arrays;

import static utility.formatting.WordListCreator.createWordList;
import static config.Globals.path_for_notes;
import static utility.path.PathCreator.createCompletePath;


public class Note {
    private Path completePath;
    private String noteName;
    private String[] wordList;
    private String[] lineList;
    private String content;
    private String noteForGraphicalProcessing;

    private Note() {
    }

    public static Note initializeNote(final String noteName){
        final Note note = new Note();
        final NoteReader noteReader = new NoteReader();
        note.noteName = noteName;
        note.completePath = createCompletePath(noteName, path_for_notes);
        note.content = noteReader.readNoteForNoteProcessing(note.completePath);
        note.noteForGraphicalProcessing = noteReader.readNote(note.completePath);
        note.lineList = note.createLineList(note.content);
        note.wordList = createWordList(note.content);
        note.wordList = note.removeEmptyLines(note.wordList);
        return note;
    }

    private String[] createLineList(final String content) {//createLineList sollte nach dem Information Expert Pattern
        // keine static helper Methode sein, weil die Klasse Note bereits über Vorwissen (konkret die String Instanz content) verfügt
        return content.split("\n");
    }

    public String[] removeEmptyLines(final String... words) {  //removeEmptyLines sollte nach dem Information Expert Pattern
        // keine static helper Methode sein, weil die Klasse Note bereits über Vorwissen (konkret die String[] Instanz wordList) verfügt
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

    public String getNoteForGraphicalProcessing() {
        return noteForGraphicalProcessing;
    }

    public String getContent() {
        return content;
    }
}
