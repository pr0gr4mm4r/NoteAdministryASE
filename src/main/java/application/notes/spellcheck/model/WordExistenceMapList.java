package application.notes.spellcheck.model;

import application.WordExistenceMap;
import application.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.ArrayList;
import java.util.List;

public class WordExistenceMapList extends ArrayList<WordExistenceMap> {
    public void fill(List<String[]> wordListList){
        for (String[] wordList : wordListList) {
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            spellCheckerRaw.checkSpelling(wordList);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            WordExistenceMap wordExistence = new WordExistenceMap(wordsInLexicon, wordsNotInLexicon);
            this.add(wordExistence);
        }
    }
}
