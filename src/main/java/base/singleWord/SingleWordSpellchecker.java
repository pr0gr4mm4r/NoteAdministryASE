package base.singleWord;

import base.notes.processors.SingleNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.WordExistenceMap;

import static base.config.Globals.scanner;

import java.util.*;

public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();

    public SingleWordSpellchecker() {
        System.out.println("Provide an english word to check if it is correctly spelled:");
        String wordToCheckSpelling = scanner.nextLine();
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
