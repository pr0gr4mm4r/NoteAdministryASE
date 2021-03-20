package base.notes.find.multi;

import base.notes.processors.multi.MultiNoteProcessor;
import base.start.NoteAdministryStart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static base.notes.find.single.SingleNoteWordFinder.countOccurrenceOfWord;

public class OverviewWordFinder {

    private final String keyword;
    private final MultiNoteProcessor multiNoteProcessor;

    public OverviewWordFinder(String keyword, MultiNoteProcessor multiNoteProcessor) {
        this.keyword = keyword;
        this.multiNoteProcessor = multiNoteProcessor;
        try {
            composeOverview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void composeOverview() throws IOException {
        List<Path> pathsToNotesList = multiNoteProcessor.getPathList();
        List<String> noteList = createNoteList(pathsToNotesList);
        List<Map<Integer, Integer>> wordOccurenceOverview = new ArrayList<>();
        composeWordOccurrenceOverview(pathsToNotesList, wordOccurenceOverview);
        if (NoteAdministryStart.programRun) {
            printResults(noteList, wordOccurenceOverview);
        }
    }

    public void composeWordOccurrenceOverview(List<Path> pathsToNotesList, List<Map<Integer, Integer>> wordOccurenceOverview) throws IOException {
        for (Path value : pathsToNotesList) {
            Map<Integer, Integer> wordOccurenceSingleNote = composeWordOccurenceSingleNote(value);
            wordOccurenceOverview.add(wordOccurenceSingleNote);
        }
    }

    public Map<Integer, Integer> composeWordOccurenceSingleNote(Path value) throws IOException {
        Map<Integer, Integer> wordOccurenceSingleNote = new HashMap<>();
        long lineLength = Files.lines(value).count();
        List<List<String>> fileContent = new ArrayList<>();
        for (int j = 0; j < lineLength; j++) {
            fileContent.add(new ArrayList<>());
            String singleFileLine = Files.readAllLines(value).get(j);
            fileContent.get(j).add(singleFileLine);
            int occurenceOfWord = countOccurrenceOfWord(keyword, singleFileLine);
            wordOccurenceSingleNote.put(j + 1, occurenceOfWord);
        }
        return wordOccurenceSingleNote;
    }

    public List<String> createNoteList(List<Path> pathsToNotesList) {
        return pathsToNotesList.stream().map(Path::toString).collect(Collectors.toList());
    }

    public void printResults(List<String> noteList, List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        final boolean keywordPresent = checkIfKeyWordIsPresent(keyWordOccurenceOverview);
        if (!keywordPresent) {
            System.out.println("The keyword '" + keyword + "' is not present in any of your notes!");
            return;
        }
        System.out.println("The keyword '" + keyword + "' does occur in following notes: ");
        List<Integer> rememberRemoved = createRememberRemovedList(keyWordOccurenceOverview);

        for (Integer integer : rememberRemoved) {
            noteList.remove(integer);
        }
        for (int j = 0; j < keyWordOccurenceOverview.size(); j++) {
            int summedOccurrenceInNote = keyWordOccurenceOverview.get(j).values().stream().reduce(0, Integer::sum);
            Map<Integer, Integer> linesWithOccurrence = keyWordOccurenceOverview.get(j).entrySet().stream().filter(
                    entry -> entry.getValue() > 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            System.out.println(noteList.get(j) + ": " + summedOccurrenceInNote + " (lines: " + linesWithOccurrence.entrySet() + ")");
        }
    }

    public List<Integer> createRememberRemovedList(List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        List<Integer> rememberRemoved = new ArrayList<>();
        for (int i = 0; i < keyWordOccurenceOverview.size(); i++) {
            Map<Integer, Integer> keyWordOccurenceSingleNote = keyWordOccurenceOverview.get(i);
            boolean keyWordNotPresent = keyWordOccurenceSingleNote.values().stream().allMatch(v -> v == 0);
            if (keyWordNotPresent) {
                rememberRemoved.add(i);
                keyWordOccurenceSingleNote.remove(i);
            }
        }
        return rememberRemoved;
    }

    public boolean checkIfKeyWordIsPresent(final List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        boolean keywordPresent = false;
        for (int i = 0; i < keyWordOccurenceOverview.size(); i++) {
            if (!keyWordOccurenceOverview.get(i).values().stream().allMatch(v -> v == 0)) {
                keywordPresent = true;
            }
        }
        return keywordPresent;
    }

    public String getKeyword() {
        return keyword;
    }

    public MultiNoteProcessor getMultiNoteProcessor() {
        return multiNoteProcessor;
    }
}
