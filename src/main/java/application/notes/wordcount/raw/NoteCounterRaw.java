package application.notes.wordcount.raw;

import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.processors.single.SingleNoteProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static config.Globals.path_for_notes;

public class NoteCounterRaw {
    private long lineCount;
    private int wordCount;
    private SingleNoteProcessor singleNoteProcessor;
    private MultiNoteProcessor multiNoteProcessor;

    private List<Long> lineCountList = new ArrayList<>();
    private List<Integer> wordCountList = new ArrayList<>();

    public NoteCounterRaw(String noteName) {
        singleNoteProcessor = new SingleNoteProcessor(noteName);
        Path path = singleNoteProcessor.getCompletePath();
        lineCount = countLinesOfNote(path);
        wordCount = countWordsOfNote(singleNoteProcessor);
    }

    public NoteCounterRaw() {
        multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        List<Path> pathList = multiNoteProcessor.getPathList();
        lineCountList = countLinesOfNotes(pathList, lineCountList);
        List<String[]> wordListList = multiNoteProcessor.getWordListList();
        wordCountList = countWordsOfNotes(wordListList);
    }

    private List<Long> countLinesOfNotes(List<Path> pathList, List<Long> lineCountList) {
        for (Path path: pathList){
            long lineCount = countLinesOfNote(path);
            lineCountList.add(lineCount);
        }
        return lineCountList;
    }

    public long countLinesOfNote(Path completePath) {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.count();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public int countWordsOfNote(SingleNoteProcessor singleNoteProcessor) {
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

    public void setLineCount(long lineCount) {
        this.lineCount = lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public List<Long> getLineCountList() {
        return lineCountList;
    }

    public void setLineCountList(List<Long> lineCountList) {
        this.lineCountList = lineCountList;
    }

    public List<Integer> getWordCountList() {
        return wordCountList;
    }

    public void setWordCountList(List<Integer> wordCountList) {
        this.wordCountList = wordCountList;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
}
