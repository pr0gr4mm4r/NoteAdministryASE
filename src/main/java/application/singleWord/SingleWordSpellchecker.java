package application.singleWord;

import utility.formatting.StringRepresentation.Result;
import application.notes.spellcheck.raw.SpellCheckerRaw;
import utility.map.WordExistenceMap;

import java.util.*;

import static application.notes.spellcheck.raw.SpellCheckerRaw.initializeSpellCheckerRaw;
import static utility.formatting.WordListCreator.createWordList;
import static utility.map.WordExistenceMap.initializeWordExistenceMap;


public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;

    private SingleWordSpellchecker() {

    }

    public static SingleWordSpellchecker initializeSingleWordSpellChecker(final String wordToCheckSpelling){
        final SingleWordSpellchecker singleWordSpellchecker = new SingleWordSpellchecker();
        final String[] wordList = createWordList(wordToCheckSpelling);
        singleWordSpellchecker.spellCheckerRaw = initializeSpellCheckerRaw(wordList);
        final List<String> wordsInLexicon = singleWordSpellchecker.spellCheckerRaw.getWordsInLexicon();
        final List<String> wordsNotInLexicon = singleWordSpellchecker.spellCheckerRaw.getWordsNotInLexicon();
        final WordExistenceMap wordExistenceMap = initializeWordExistenceMap(wordsInLexicon, wordsNotInLexicon);
        final Result result = new Result(wordExistenceMap.toString());
        result.print();
        return singleWordSpellchecker;
    }
}
