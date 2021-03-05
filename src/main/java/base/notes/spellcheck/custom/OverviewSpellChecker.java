package base.notes.spellcheck.custom;

import base.notes.processors.MultiNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceMap;

import java.util.*;


public class OverviewSpellChecker {
    private final List<Map<String, Boolean>> wordExistenceOverview = new ArrayList<>();

    public OverviewSpellChecker() {
        final MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        final List<String[]> wordListList = multiNoteProcessor.getWordListList();
        for (String[] wordList : wordListList) {
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            spellCheckerRaw.checkSpelling(wordList);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            WordExistenceMap wordExistenceModel = new WordExistenceMap();
            wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
            wordExistenceOverview.add(wordExistenceModel);
        }
        System.out.println(wordExistenceOverview);
    }

    public List<Map<String, Boolean>> getWordExistenceOverview() {
        return wordExistenceOverview;
    }
}
