package application.notes.wordcount.raw;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.processors.single.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static application.notes.processors.single.Note.*;
import static config.Globals.path_for_notes;

public class NoteCounterRaw {
    private long lineCount;
    private int wordCount;

    private final List<Long> lineCountList = new ArrayList<>();
    private List<Integer> wordCountList = new ArrayList<>();

    public NoteCounterRaw() {

    }

    public static NoteCounterRaw initializeNoteCounterRaw(String noteName){
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        Note note = initializeNote(noteName);
        Path path = note.getCompletePath();
        noteCounterRaw.lineCount = countLinesOfNote(path);
        noteCounterRaw.wordCount = noteCounterRaw.countWordsOfNote(note);
        return noteCounterRaw;
    }

    public static NoteCounterRaw initializeNoteCounterRaw() throws NoFilesInDirectoryException {
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        NoteStack noteStack = NoteStack.initializeNoteStack(path_for_notes);
        List<Path> pathList = noteStack.getPathList();
        countLinesOfNotes(pathList, noteCounterRaw.lineCountList);
        List<String[]> wordListList = noteStack.getWordListList();
        noteCounterRaw.wordCountList = noteCounterRaw.countWordsOfNotes(wordListList);
        return noteCounterRaw;
    }


    private static void countLinesOfNotes(List<Path> pathList, List<Long> lineCountList) {
        for (Path path: pathList){
            long lineCount = countLinesOfNote(path);
            lineCountList.add(lineCount);
        }
    }

    public static long countLinesOfNote(Path completePath) {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.count();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int countWordsOfNote(Note singleNoteProcessor) {
        wordCount = singleNoteProcessor.getWordList().length;
        return wordCount;
    }

    public List<Integer> countWordsOfNotes(List<String[]> wordListList) {
        for (String[] strings : wordListList) {
            Integer wordCount = strings.length;
            wordCountList.add(wordCount);
        }
        return wordCountList;
    }

    public long getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public List<Long> getLineCountList() {
        return lineCountList;
    }

    public List<Integer> getWordCountList() {
        return wordCountList;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
}
