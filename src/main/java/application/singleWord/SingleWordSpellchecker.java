package application.singleWord;

import application.notes.spellcheck.raw.SpellCheckerRaw;
import application.WordExistenceMap;

import java.util.*;

import static application.notes.processors.single.Note.*;

public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;

    public SingleWordSpellchecker(String wordToCheckSpelling) {
        String[] wordList = createWordList(wordToCheckSpelling);
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        WordExistenceMap wordExistenceMap = new WordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        System.out.println(wordExistenceMap);
    }
}
