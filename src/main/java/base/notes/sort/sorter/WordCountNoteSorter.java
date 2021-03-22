package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.logfiles.crud.declare.single.LogFileDeclarator;
import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.sort.formatter.WordCountNoteSorterResultFormatter;
import base.notes.sort.model.maps.StringIntegerMap;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.util.*;
import java.util.Map.Entry;

import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;


public class WordCountNoteSorter implements Sorter {
    List<String> noteList;
    List<String> nameList;
    List<Integer> wordCountList;
    MultiNoteProcessor multiNoteProcessor;
    NoteCounterRaw noteCounterRaw;

    public WordCountNoteSorter() {

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
        multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        noteCounterRaw = new NoteCounterRaw();
        noteList = multiNoteProcessor.getNoteList();
        nameList = new ArrayList<>(multiNoteProcessor.getNoteNames());
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
    public void print(String formattedResult) {
        System.out.println(formattedResult);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            new LogFileDeclarator(formattedResult, "Sorting Notes by Quantity of Rhymes");
        }
    }


    public List<String> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<String> noteList) {
        this.noteList = noteList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<Integer> getWordCountList() {
        return wordCountList;
    }

    public void setWordCountList(List<Integer> wordCountList) {
        this.wordCountList = wordCountList;
    }
}
