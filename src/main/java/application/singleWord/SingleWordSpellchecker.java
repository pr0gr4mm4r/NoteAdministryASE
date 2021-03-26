package application.singleWord;

import application.notes.spellcheck.raw.SpellCheckerRaw;
import utility.map.WordExistenceMap;

import java.util.*;

import static utility.map.WordExistenceMap.initializeWordExistenceMap;
import static application.notes.processors.single.Note.*;
import static application.notes.spellcheck.raw.SpellCheckerRaw.*;

public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;

    public SingleWordSpellchecker(String wordToCheckSpelling) {
        final String[] wordList = createWordList(wordToCheckSpelling);
        spellCheckerRaw = initializeSpellCheckerRaw(wordList);
        final List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        final List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        final WordExistenceMap wordExistenceMap = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        System.out.println(wordExistenceMap);
    }
}
