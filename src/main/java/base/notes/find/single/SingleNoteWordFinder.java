package base.notes.find.single;

import base.notes.processors.single.SingleNoteProcessor;

import java.util.*;
import java.util.stream.Collectors;

import static base.start.NoteAdministryStart.programRun;

public class SingleNoteWordFinder {
    private Map<Integer, Integer> wordOccurrence;

    public SingleNoteWordFinder(String keyword, String nameOfNote) {
        String[] lineList = initializeLinelist(nameOfNote);
        wordOccurrence = composeOccurrenceMap(lineList, keyword);
        if (programRun) {
            printResults();
        }
    }
    public SingleNoteWordFinder() {

    }

    public String[] initializeLinelist(String nameOfNote) {
        SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor(nameOfNote);
        String[] lineList = singleNoteProcessor.getLineList();
        return lineList;
    }

    public Map<Integer, Integer> composeOccurrenceMap(String[] lineList, String keyword) {
        Map<Integer, Integer> wordOccurence = new HashMap<>();
        List<List<String>> fileContent = new ArrayList<>();
        final long lineLength = lineList.length;
        for (int j = 0; j < lineLength; j++) {
            fileContent.add(new ArrayList<>());
            String line = lineList[j];
            fileContent.get(j).add(line);
            int occurenceOfWord = countOccurrenceOfWord(keyword, line);
            wordOccurence.put(j + 1, occurenceOfWord);
        }
        wordOccurence = filterWordOccurrence(wordOccurence);
        return wordOccurence;
    }

    public Map<Integer, Integer> filterWordOccurrence(Map<Integer, Integer> wordOccurence) {
        wordOccurence = wordOccurence.entrySet().stream().filter(
                entry -> entry.getValue() > 0).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return wordOccurence;

    }

    public int countOccurrenceOfWord(final String word, final String singleFileLine) {
        final int wordLength = word.length();
        final int oldLineLength = singleFileLine.length();
        final int newLineLength = singleFileLine.replaceAll(word, "").length();
        final int oldNewLineLengthDifference = oldLineLength - newLineLength;
        final int occurrence = oldNewLineLengthDifference / wordLength;
        return occurrence;
    }

    private void printResults() {
        System.out.println(wordOccurrence);
    }
}
