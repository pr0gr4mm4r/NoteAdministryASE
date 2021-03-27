package application.notes.ui.raw;

import utility.map.WordExistenceMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import rita.RiTa;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.spellcheck.raw.SpellCheckerRaw.*;

public class RhymeCounterRaw {
    private int counter = 0;
    private final String content;

    public RhymeCounterRaw(final String content) {
        this.content = content;
    }

    public Integer calcRhymes() {
        WordExistenceMap wordExistence = initializeWordExistence();
        initializeWordsInLexiconEntries(wordExistence);
        final List<Entry<String, Boolean>> wordsInLexiconEntries = initializeWordsInLexiconEntries(wordExistence);
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

    private List<Entry<String, Boolean>> initializeWordsInLexiconEntries(WordExistenceMap wordExistence) {
        return wordExistence.entrySet().stream().
                filter(Entry::getValue).collect(Collectors.toList());
    }

    private WordExistenceMap initializeWordExistence() {
        final String[] splittedContent = content.split("\\s+");
        final SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(splittedContent);
        final List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        final List<String> wordsnotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        final WordExistenceMap wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsnotInLexicon);
        return wordExistence;
    }
}




