package base.notes.spellcheck.model;

import base.notes.ui.WordExistenceMap;
import base.notes.spellcheck.raw.SpellCheckerRaw;

import java.util.ArrayList;
import java.util.List;

public class WordExistenceMapList extends ArrayList<WordExistenceMap> {
    public void fill(List<String[]> wordListList){
        for (String[] wordList : wordListList) {
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            spellCheckerRaw.checkSpelling(wordList);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            WordExistenceMap wordExistenceModel = new WordExistenceMap();
            wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
            this.add(wordExistenceModel);
        }
    }
}
