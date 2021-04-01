package application.notes.find.multi;

import application.notes.find.single.SingleNoteWordFinder;
import application.notes.processors.multi.NoteStack;
import application.start.NoteAdministryStart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static application.start.NoteAdministryStart.programRun;

public class OverviewWordFinder {

    private final String keyword;
    private final NoteStack noteStack;
    private final SingleNoteWordFinder singleNoteWordFinder;

    public OverviewWordFinder(final String keyword, final NoteStack noteStack) {
        this.keyword = keyword;
        this.noteStack = noteStack;
        this.singleNoteWordFinder = new SingleNoteWordFinder();
    }

    public void composeOverview() throws IOException {
        final List<Path> pathsToNotesList = noteStack.getPathList();
        final List<String> noteList = createNoteList(pathsToNotesList);
        final List<Map<Integer, Integer>> wordOccurenceOverview = new ArrayList<>();
        composeWordOccurrenceOverview(pathsToNotesList, wordOccurenceOverview);
        if (programRun) {
            printResults(noteList, wordOccurenceOverview);
        }
    }

    private void composeWordOccurrenceOverview(final List<Path> pathsToNotesList, final List<Map<Integer, Integer>> wordOccurenceOverview) throws IOException {
        for (final Path value : pathsToNotesList) {
            final Map<Integer, Integer> wordOccurenceSingleNote = composeWordOccurrenceSingleNote(value);
            wordOccurenceOverview.add(wordOccurenceSingleNote);
        }
    }

    public Map<Integer, Integer> composeWordOccurrenceSingleNote(final Path path) throws IOException {
        final Map<Integer, Integer> wordOccurenceSingleNote = new HashMap<>();
        final long lineLength = Files.lines(path).count();
        final List<List<String>> fileContent = new ArrayList<>();
        for (int j = 0; j < lineLength; j++) {
            fileContent.add(new ArrayList<>());
            final String singleFileLine = Files.readAllLines(path).get(j);
            fileContent.get(j).add(singleFileLine);
            final int occurenceOfWord = singleNoteWordFinder.countOccurrenceOfWord(keyword, singleFileLine);
            wordOccurenceSingleNote.put(j + 1, occurenceOfWord);
        }
        return wordOccurenceSingleNote;
    }

    public List<String> createNoteList(final List<Path> pathsToNotesList) {
        return pathsToNotesList.stream().map(Path::toString).collect(Collectors.toList());
    }

    public void printResults(final List<String> noteList, final List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        final boolean keywordPresent = checkIfKeyWordIsPresent(keyWordOccurenceOverview);
        if (!keywordPresent) {
            System.out.println("The keyword '" + keyword + "' is not present in any of your notes!");
            return;
        }
        System.out.println("The keyword '" + keyword + "' does occur in following notes: ");
        final List<Integer> rememberRemoved = createRememberRemovedList(keyWordOccurenceOverview);

        for (final Integer integer : rememberRemoved) {
            noteList.remove(integer);
        }
        for (int j = 0; j < keyWordOccurenceOverview.size(); j++) {
            final int summedOccurrenceInNote = keyWordOccurenceOverview.get(j).values().stream().reduce(0, Integer::sum);
            final Map<Integer, Integer> linesWithOccurrence = keyWordOccurenceOverview.get(j).entrySet().stream().filter(
                    entry -> entry.getValue() > 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            System.out.println(noteList.get(j) + ": " + summedOccurrenceInNote + " (lines: " + linesWithOccurrence.entrySet() + ")");
        }
    }

    public List<Integer> createRememberRemovedList(final List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        final List<Integer> rememberRemoved = new ArrayList<>();
        for (int i = 0; i < keyWordOccurenceOverview.size(); i++) {
            final Map<Integer, Integer> keyWordOccurenceSingleNote = keyWordOccurenceOverview.get(i);
            final boolean keyWordNotPresent = keyWordOccurenceSingleNote.values().stream().allMatch(v -> v == 0);
            if (keyWordNotPresent) {
                rememberRemoved.add(i);
                keyWordOccurenceSingleNote.remove(i);
            }
        }
        return rememberRemoved;
    }

    public boolean checkIfKeyWordIsPresent(final List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        for (final Map<Integer, Integer> integerIntegerMap : keyWordOccurenceOverview) {
            if (!integerIntegerMap.values().stream().allMatch(v -> v == 0)) {
                return true;
            }
        }
        return false;
    }

    public String getKeyword() {
        return keyword;
    }

    public NoteStack getNoteStack() {
        return noteStack;
    }
}
