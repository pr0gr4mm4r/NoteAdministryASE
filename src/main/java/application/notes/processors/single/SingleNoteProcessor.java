package application.notes.processors.single;

import application.notes.processors.abstraction.Processor;

import java.nio.file.Path;
import java.util.Arrays;

import static application.redg.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static application.notes.crud.read.single.NoteReader.readNote;
import static application.notes.crud.read.single.NoteReader.readNoteForNoteProcessing;


public class SingleNoteProcessor implements Processor {
    private Path completePath;
    private String noteName;
    private String[] wordList;
    private String[] lineList;
    private String note;
    private String noteForGraphicalProcessing;

    public SingleNoteProcessor(String noteName) {
        this.noteName = noteName;
        completePath = createCompletePath(noteName, path_for_notes);
        note = readNoteForNoteProcessing(completePath);
        noteForGraphicalProcessing = readNote(completePath);
        lineList = createLineList(note);
        wordList = createWordList(note);
        wordList = removeEmptyLines(wordList);
    }

    public SingleNoteProcessor(){

    }

    private String[] createLineList(String note) {
        return note.split("\n");
    }

    public String[] removeEmptyLines(String[] words) {
        return Arrays.stream(words).filter(word->!word.equals("")).toArray(String[]::new);
    }

    public String[] createWordList(String string){
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
