package application.notes.ui.raw;

import utility.map.WordExistenceMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import rita.RiTa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.spellcheck.raw.SpellCheckerRaw.*;

public class RhymeCounterRaw {
    private String content;

    public RhymeCounterRaw(String content) {
        this.content = content;
    }

    public Integer calcRhymes() {
        int counter = 0;
        String[] splittedContent = content.split("\\s+");
        SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(splittedContent);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsnotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        WordExistenceMap wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsnotInLexicon);
        List<Map.Entry<String, Boolean>> wordsInLexiconEntries = wordExistence.entrySet().stream().
                filter(Map.Entry::getValue).collect(Collectors.toList());
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
        return counter;
    }
}




