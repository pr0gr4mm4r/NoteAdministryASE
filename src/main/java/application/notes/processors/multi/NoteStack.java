package application.notes.processors.multi;

import application.notes.processors.abstraction.NoteProcessor;
import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import application.notes.processors.single.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static application.notes.processors.single.Note.*;

public class NoteStack implements NoteProcessor {
    protected List<Path> pathList;
    protected List<String> noteContentList;
    protected final List<String[]> separatedWordListList = new ArrayList<>();
    public Set<String> noteNames = new HashSet<>();
    public List<Note> notes = new ArrayList<>();

    public NoteStack() {

    }

    public static NoteStack initializeNoteStack(final String path) throws NoFilesInDirectoryException, IOException {
        final NoteStack noteStack = new NoteStack();
        noteStack.noteNames = noteStack.listNoteNames(path);
        if (noteStack.noteNames.isEmpty()) {
            throw new NoFilesInDirectoryException(path);
        }
        noteStack.notes = noteStack.initializeNotes(noteStack);
        noteStack.pathList = noteStack.createPathList(noteStack.notes);
        noteStack.noteContentList = noteStack.createNoteContentList(noteStack.notes);
        noteStack.separateWordsForEachNote(noteStack.notes, noteStack.separatedWordListList);
        return noteStack;
    }

    private List<Note> initializeNotes(NoteStack noteStack) {
        List<String> noteNames = new ArrayList<>(noteStack.noteNames);
        for (final String noteName : noteNames) {
            noteStack.notes.add(initializeNote(noteName));
        }
        return noteStack.notes;
    }

    protected void separateWordsForEachNote(final List<Note> noteContentList, final List<String[]> separatedWordListList) {
        for (final Note noteContent : noteContentList) {
            final String[] separatedWords = noteContent.getWordList();
            separatedWordListList.add(separatedWords);
        }
    }

    protected List<String> createNoteContentList(final List<Note> noteList) {
        final List<String> noteContentList = new ArrayList<>();
        for (final Note note : noteList) {
            final String noteContent = note.getContent();
            noteContentList.add(noteContent);
        }
        return noteContentList;
    }

    protected List<Path> createPathList(final List<Note> notes) {
        final List<Path> pathList = new ArrayList<>();
        for (final Note note : notes) {
            pathList.add(note.getCompletePath());
        }
        return pathList;
    }

    public Set<String> listNoteNames(final String path) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(path))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public List<Path> getPathList() {
        return pathList;
    }

    public List<String> getNoteContentList() {
        return noteContentList;
    }

    public Set<String> getNoteNames() {
        return noteNames;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<String[]> getSeparatedWordListList() {
        return separatedWordListList;
    }
}
