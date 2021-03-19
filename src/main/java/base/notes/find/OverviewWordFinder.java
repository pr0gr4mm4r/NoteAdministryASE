package base.notes.find;

import base.notes.processors.MultiNoteProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static base.config.Globals.scanner;
import static base.notes.find.SingleNoteWordFinder.countOccurrenceOfWord;

public class OverviewWordFinder {
    private final String keyword;
    private final MultiNoteProcessor multiNoteProcessor;

    public OverviewWordFinder() {
        System.out.println("type in a keyword to search:");
        keyword = scanner.nextLine();
        multiNoteProcessor = new MultiNoteProcessor();
        try {
            composeOverview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void composeOverview() throws IOException {
            List<Path> pathsToNotesList = multiNoteProcessor.getPathList();
            List<String> noteList = createNoteList(pathsToNotesList);
            List<Map<Integer, Integer>> wordOccurenceOverview = new ArrayList<>();
            composeWordOccurrenceOverview(pathsToNotesList, wordOccurenceOverview);
            printResults(noteList, wordOccurenceOverview);
    }

    private void composeWordOccurrenceOverview(List<Path> pathsToNotesList, List<Map<Integer, Integer>> wordOccurenceOverview) throws IOException{
        for (Path value : pathsToNotesList) {
            Map<Integer, Integer> wordOccurenceSingleNote = new HashMap<>();
            List<List<String>> fileContent = new ArrayList<>();
            long lineLength = Files.lines(value).count();
            for (int j = 0; j < lineLength; j++) {
                fileContent.add(new ArrayList<>());
                String singleFileLine = Files.readAllLines(value).get(j);
                fileContent.get(j).add(singleFileLine);
                int occurenceOfWord = countOccurrenceOfWord(keyword, singleFileLine);
                wordOccurenceSingleNote.put(j + 1, occurenceOfWord);
            }
            wordOccurenceOverview.add(wordOccurenceSingleNote);
        }
    }

    private List<String> createNoteList(List<Path> pathsToNotesList) {
        return pathsToNotesList.stream().map(Path::toString).collect(Collectors.toList());
    }

    private void printResults(List<String> noteList, List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        final boolean keywordPresent = checkIfKeyWordIsPresent(keyWordOccurenceOverview);
        if (!keywordPresent) {
            System.out.println("The keyword '" + keyword + "' is not present in any of your notes!");
            return;
        }
        System.out.println("The keyword '" + keyword + "' does occur in following notes: ");
        List<Integer> rememberRemoved = new ArrayList<>();
        for (int i = 0; i < keyWordOccurenceOverview.size(); i++) {
            Map<Integer, Integer> keyWordOccurenceSingleNote = keyWordOccurenceOverview.get(i);
            boolean keyWordNotPresent = keyWordOccurenceSingleNote.values().stream().allMatch(v -> v == 0);
            if (keyWordNotPresent) {
                rememberRemoved.add(i);
                keyWordOccurenceSingleNote.remove(i);
            }
        }
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

    private boolean checkIfKeyWordIsPresent(final List<Map<Integer, Integer>> keyWordOccurenceOverview) {
        boolean keywordPresent = false;
        for (int i = 0; i < keyWordOccurenceOverview.size(); i++) {
            if (!keyWordOccurenceOverview.get(i).values().stream().allMatch(v -> v == 0)) {
                keywordPresent = true;
            }
        }
        return keywordPresent;
    }
}
