package application.notes.spellcheck.model;

import utility.map.WordExistenceMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.ArrayList;
import java.util.List;

import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.spellcheck.raw.SpellCheckerRaw.initializeSpellCheckerRaw;

public class WordExistenceMapList extends ArrayList<WordExistenceMap> {
    public void fill(List<String[]> wordListList){
        for (String[] wordList : wordListList) {
            SpellCheckerRaw spellCheckerRaw = initializeSpellCheckerRaw(wordList);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            WordExistenceMap wordExistence = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
            this.add(wordExistence);
        }
    }
}
