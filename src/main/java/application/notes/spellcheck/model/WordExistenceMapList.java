package application.notes.spellcheck.model;

import utility.map.WordExistenceMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.ArrayList;
import java.util.List;

import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.spellcheck.raw.SpellCheckerRaw.initializeSpellCheckerRaw;

public class WordExistenceMapList extends ArrayList<WordExistenceMap> {

    private WordExistenceMapList() {

    }

    public static WordExistenceMapList initializeWordExistenceMapList(final List<String[]> wordListList) {
        final WordExistenceMapList wordExistenceMapList = new WordExistenceMapList();
        wordExistenceMapList.fill(wordListList);
        return wordExistenceMapList;
    }

    private void fill(final List<String[]> wordListList) {
        for (final String[] wordList : wordListList) {
            final SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(wordList);
            final List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            final List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            final WordExistenceMap wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
            this.add(wordExistence);
        }
    }
}
