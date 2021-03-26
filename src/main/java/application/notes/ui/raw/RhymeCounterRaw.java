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
        final String[] splittedContent = content.split("\\s+");
        final SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(splittedContent);
        final List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        final List<String> wordsnotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        final WordExistenceMap wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsnotInLexicon);
        final List<Map.Entry<String, Boolean>> wordsInLexiconEntries = wordExistence.entrySet().stream().
                filter(Map.Entry::getValue).collect(Collectors.toList());
        final int size = wordsInLexiconEntries.size();
        for (int j = 0; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                final String firstWord = wordsInLexiconEntries.get(j).getKey();
                final String secondWord = wordsInLexiconEntries.get(k).getKey();
                if (RiTa.isRhyme(firstWord, secondWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}




