package application.notes.sort.sorter;

import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.formatter.WordCountNoteSorterResultFormatter;
import application.notes.sort.model.maps.StringIntegerMap;
import application.notes.wordcount.raw.NoteCounterRaw;

import java.util.*;
import java.util.Map.Entry;

import static application.logfiles.crud.declare.single.LogFileDeclarator.initializeLogFileDeclarator;
import static config.Globals.scanner;


public class WordCountNoteSorter implements Sorter {
    List<String> noteList;
    List<String> nameList;
    List<Integer> wordCountList;
    NoteStack noteStack;
    NoteCounterRaw noteCounterRaw;

    public WordCountNoteSorter(NoteStack noteStack) {
        this.noteStack = noteStack;
    }

    @Override
    public Map initialize() {
        initializeVariables();
        StringIntegerMap<String, Integer> wordCountMap = new StringIntegerMap<>();
        for (int i = 0; i < noteList.size(); i++) {
            String nameOfNote = nameList.get(i);
            Integer wordCount = wordCountList.get(i);
            wordCountMap.put(nameOfNote, wordCount);
        }
        return wordCountMap;
    }

    private void initializeVariables() {
        noteCounterRaw = new NoteCounterRaw();
        noteList = noteStack.getNoteList();
        nameList = new ArrayList<>(noteStack.getNoteNames());
        wordCountList = noteCounterRaw.getWordCountList();
    }

    @Override
    public List sort(Map wordCountMap) {
        Set<Entry<String, Integer>> wordCountSet = wordCountMap.entrySet();
        List<Entry<String, Integer>> finalWordCountList = new ArrayList<>(wordCountSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalWordCountList.sort(valueComparator);
        return finalWordCountList;
    }

    @Override
    public String format(List finalWordCountList) {
        WordCountNoteSorterResultFormatter wordCountNoteSorterResultFormatter = new WordCountNoteSorterResultFormatter();
        String formattedResult = wordCountNoteSorterResultFormatter.formatList(finalWordCountList);
        return formattedResult;
    }

    @Override
    public void dialogue(String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            createLogFile(formattedResult);
        }
    }

    @Override
    public void createLogFile(String formattedResult) {
        initializeLogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Rhymes");
    }

}
