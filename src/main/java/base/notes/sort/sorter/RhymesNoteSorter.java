package base.notes.sort.sorter;

import base.interfaces.Sorter;
import base.notes.processors.MultiNoteProcessor;
import base.WordExistenceMap;
import rita.RiTa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static base.notes.spellcheck.raw.SpellCheckerRaw.filterNegatives;
import static base.notes.spellcheck.raw.SpellCheckerRaw.filterPositives;

public class RhymesNoteSorter implements Sorter {
    public String sort() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String[]> noteList = multiNoteProcessor.getWordListList();
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        Map<String, Integer> rhymeOverview = new HashMap<>();
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
        System.out.println(rhymeOverview);
        return "";
    }
}
