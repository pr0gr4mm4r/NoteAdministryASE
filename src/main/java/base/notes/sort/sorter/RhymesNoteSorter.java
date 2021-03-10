package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.logfiles.crud.declare.LogFileDeclarator;
import base.notes.processors.MultiNoteProcessor;
import base.WordExistenceMap;
import base.notes.sort.formatter.RhymesNoteSorterResultFormatter;
import base.notes.sort.model.maps.StringIntegerMap;
import rita.RiTa;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static base.config.Globals.scanner;
import static base.notes.spellcheck.raw.SpellCheckerRaw.filterNegatives;
import static base.notes.spellcheck.raw.SpellCheckerRaw.filterPositives;

public class RhymesNoteSorter implements Sorter {
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String[]> noteList = multiNoteProcessor.getWordListList();
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        Map<String, Integer> rhymeOverview = new StringIntegerMap<>();
        int counter;
        for (int i = 0; i < noteList.size(); i++) {
            counter = 0;
            List<String> wordsInLexicon = filterPositives(noteList.get(i));
            List<String> wordsNotInLexicon = filterNegatives(noteList.get(i));
            WordExistenceMap wordExistenceModel = new WordExistenceMap();
            wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
            List<Entry<String, Boolean>> wordsInLexiconEntries = wordExistenceModel.entrySet().stream().
                    filter(Entry::getValue).collect(Collectors.toList());
            final int size = wordsInLexiconEntries.size();
            for (int j = 0; j < size - 1; j++) {
                for (int k = j + 1; k < size; k++) {
                    String firstWord = wordsInLexiconEntries.get(j).getKey();
                    String secondWord = wordsInLexiconEntries.get(k).getKey();
                    if (RiTa.isRhyme(firstWord, secondWord)) {
                        counter++;
                    }
                }
            }
            rhymeOverview.put(noteNames.get(i), counter);
        }
        Set<Entry<String, Integer>> rhymeOverviewSet = rhymeOverview.entrySet();
        List<Entry<String, Integer>> finalRhymeOverview = new ArrayList<>(rhymeOverviewSet);
        Comparator<Entry<String, Integer>> valueComparator = Comparator.comparingInt(Entry::getValue);
        finalRhymeOverview.sort(valueComparator);
        RhymesNoteSorterResultFormatter rhymesNoteSorterResultFormatter = new RhymesNoteSorterResultFormatter();
        String result = rhymesNoteSorterResultFormatter.formatList(finalRhymeOverview);
        System.out.println(result);
        System.out.println("Do you want to save the Output as a Logfile?");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if(confirmation.equals("yes")){
            new LogFileDeclarator(result, "Sorting Notes by Quantity of Rhymes");
        }
        return result;
    }
}
