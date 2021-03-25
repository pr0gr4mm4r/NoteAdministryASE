package application.notes.processors.multi;

import application.notes.processors.abstraction.Processor;

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

import static application.notes.processors.single.Note.createWordList;
import static application.notes.processors.single.Note.removeEmptyLines;
import static application.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static application.notes.crud.read.single.NoteReader.readNoteForNoteProcessing;

public class NoteStack implements Processor {
    private List<Path> pathList;
    private List<String> noteList;
    private List<String[]> separatedWordListList = new ArrayList<>();
    private List<String[]> finalWordListList;
    private Set<String> noteNames = new HashSet<>();

    public NoteStack() {

    }

    public static NoteStack initializeStack(String path){
        NoteStack noteStack = new NoteStack();
        try {
            noteStack.noteNames = noteStack.listNoteNames(path, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        noteStack.pathList = noteStack.createPathList(noteStack.noteNames, path);
        noteStack.noteList = noteStack.createNoteList(noteStack.pathList);
        noteStack.separatedWordListList = noteStack.separateWordsForEachNote(noteStack.noteList, noteStack.separatedWordListList);
        noteStack.finalWordListList = noteStack.removeEmptyLinesForEachNote(noteStack.separatedWordListList);
        return noteStack;
    }

    public List<String[]> removeEmptyLinesForEachNote(List<String[]> wordListLists) {
        for (int i = 0; i < wordListLists.size(); i++) {
            String[] filteredWordList = removeEmptyLines(wordListLists.get(i));
            wordListLists.set(i, filteredWordList);
        }
        return wordListLists;
    }

    public List<String[]> separateWordsForEachNote(List<String> noteList, List<String[]> separatedWordListList) {
        for (String note : noteList) {
            String[] separatedWords = createWordList(note);
            separatedWordListList.add(separatedWords);
        }
        return separatedWordListList;
    }

    public List<String> createNoteList(List<Path> pathList) {
        List<String> noteList = new ArrayList<>();
        for (Path path : pathList) {
            String note = readNoteForNoteProcessing(path);
            noteList.add(note);
        }
        return noteList;
    }

    public List<Path> createPathList(Set<String> strings, String path) {
        List<Path> pathList = new ArrayList<>();
        for (String string : strings) {
            pathList.add(createCompletePath(string, path));
        }
        return pathList;
    }

    public Set<String> listNoteNames(String path, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(path), depth)) {
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

    public List<String> getNoteList() {
        return noteList;
    }


    public List<String[]> getWordListList() {
        return finalWordListList;
    }

    public Set<String> getNoteNames() {
        return noteNames;
    }

}
