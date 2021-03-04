package base.notes.spellcheck.custom;

import base.notes.processors.SingleNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceModel;

import static base.config.Globals.scanner;

import java.util.*;

public class SingleWordSpellchecker {
    private SpellCheckerRaw spellCheckerRaw;
    private SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();


    public SingleWordSpellchecker() {
        String wordToCheckSpelling = scanner.nextLine();
        String[] wordList = singleNoteProcessor.createWordList(wordToCheckSpelling);
        spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.checkSpelling(wordList);
        List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
        List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
        WordExistenceModel wordExistenceModel = new WordExistenceModel();
        wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
        System.out.println(wordExistenceModel.getWordExistenceOverview());
    }
}
