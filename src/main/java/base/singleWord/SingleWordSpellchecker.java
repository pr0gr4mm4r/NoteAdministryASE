package base.singleWord;

import base.notes.processors.single.SingleNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.ui.WordExistenceMap;

import java.util.*;

public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();

    public SingleWordSpellchecker(String wordToCheckSpelling) {
        String[] wordList = singleNoteProcessor.createWordList(wordToCheckSpelling);
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        WordExistenceMap wordExistenceModel = new WordExistenceMap();
        wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
        System.out.println(wordExistenceModel);
    }
}
