package application.notes.wordcount.raw;

import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
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

    public static NoteCounterRaw initializeNoteCounterRaw(final String noteName){
        final NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        final Note note = initializeNote(noteName);
        final Path path = note.getCompletePath();
        noteCounterRaw.lineCount = countLinesOfNote(path);
        noteCounterRaw.wordCount = noteCounterRaw.countWordsOfNote(note);
        return noteCounterRaw;
    }

    public static NoteCounterRaw initializeNoteCounterRaw() throws NoFilesInDirectoryException, IOException {
        final NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
        final NoteStack noteStack = NoteStack.initializeNoteStack(path_for_notes);
        final List<Path> pathList = noteStack.getPathList();
        countLinesOfNotes(pathList, noteCounterRaw.lineCountList);
        final List<String[]> wordListList = noteStack.getSeparatedWordListList();
        noteCounterRaw.wordCountList = noteCounterRaw.countWordsOfNotes(wordListList);
        return noteCounterRaw;
    }


    private static void countLinesOfNotes(final List<Path> pathList, final List<Long> lineCountList) {
        for (final Path path: pathList){
            final long lineCount = countLinesOfNote(path);
            lineCountList.add(lineCount);
        }
    }

    public static long countLinesOfNote(final Path completePath) {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.count();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int countWordsOfNote(final Note note) {
        wordCount = note.getWords().length;
        return wordCount;
    }

    public List<Integer> countWordsOfNotes(final List<String[]> wordListList) {
        for (final String[] strings : wordListList) {
            final Integer wordCount = strings.length;
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

    public void setWordCount(final int wordCount) {
        this.wordCount = wordCount;
    }
}
