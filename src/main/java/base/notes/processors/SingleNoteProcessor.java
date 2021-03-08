package base.notes.processors;

import base.interfaces.Processor;

import java.nio.file.Path;
import java.util.Arrays;

import static base.config.Globals.path_for_notes;
import static base.notes.crud.declare.NoteDeclaratorCaller.createCompletePath;
import static base.notes.crud.read.NoteReader.readNoteForFurtherProcessing;

public class SingleNoteProcessor implements Processor {
    private Path completePath;
    private String noteName;
    private String[] wordList;
    private String[] lineList;
    private String note;

    public SingleNoteProcessor(String noteName) {
        this.noteName = noteName;
        completePath = createCompletePath(noteName);
        note = readNoteForFurtherProcessing(completePath);
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

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
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

    public void setCompletePath(Path completePath) {
        this.completePath = completePath;
    }

    public String[] getLineList() {
        return lineList;
    }

    public void setLineList(String[] lineList) {
        this.lineList = lineList;
    }
}
