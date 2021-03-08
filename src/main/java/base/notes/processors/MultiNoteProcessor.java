package base.notes.processors;

import base.interfaces.Processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static base.config.Globals.path_for_notes;
import static base.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;
import static base.notes.crud.read.NoteReader.readNoteForFurtherProcessing;

public class MultiNoteProcessor implements Processor {
    private List<Path> pathList = new ArrayList<>();
    private List<String> noteList = new ArrayList<>();
    private List<String[]> separatedWordListList = new ArrayList<>();
    private List<String[]> finalWordListList = new ArrayList<>();
    private Set<String> noteNames = new HashSet<>();
    private final SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();

    public MultiNoteProcessor() {
        try {
            noteNames = listNoteNames(path_for_notes, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pathList = createPathList(noteNames, pathList);
        noteList = createNoteList(pathList, noteList);
        separatedWordListList = separateWordsForEachNote(noteList, separatedWordListList);
        finalWordListList = removeEmptyLinesForEachNote(separatedWordListList);
    }

    public List<String[]> removeEmptyLinesForEachNote(List<String[]> wordListLists) {
        for (int i = 0; i < wordListLists.size(); i++) {
            String[] filteredWordList = singleNoteProcessor.removeEmptyLines(wordListLists.get(i));
            wordListLists.set(i, filteredWordList);
        };
        return wordListLists;
    }

    private List<String[]> separateWordsForEachNote(List<String> noteList, List<String[]> separatedWordListList) {
        for (String note : noteList) {
            String[] separatedWords = singleNoteProcessor.createWordList(note);
            separatedWordListList.add(separatedWords);
        }
        return separatedWordListList;
    }

    private List<String> createNoteList(List<Path> pathList, List<String> noteList) {
        for (Path path : pathList) {
            String note = readNoteForFurtherProcessing(path);
            noteList.add(note);
        }
        return noteList;
    }

    private List<Path> createPathList(Set<String> strings, List<Path> pathList) {
        for (String string : strings) {
            pathList.add(createCompletePath(string));
        }
        return pathList;
    }

    public Set<String> listNoteNames(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public static String getPath() {
        return path_for_notes;
    }

    public List<Path> getPathList() {
        return pathList;
    }

    public void setPathList(List<Path> pathList) {
        this.pathList = pathList;
    }

    public List<String> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<String> noteList) {
        this.noteList = noteList;
    }

    public List<String[]> getSeparatedWordListList() {
        return separatedWordListList;
    }

    public void setSeparatedWordListList(List<String[]> separatedWordListList) {
        this.separatedWordListList = separatedWordListList;
    }

    public List<String[]> getWordListList() {
        return finalWordListList;
    }

    public void setFinalWordListList(List<String[]> finalWordListList) {
        this.finalWordListList = finalWordListList;
    }

    public Set<String> getNoteNames() {
        return noteNames;
    }

    public void setNoteNames(Set<String> noteNames) {
        this.noteNames = noteNames;
    }
}
