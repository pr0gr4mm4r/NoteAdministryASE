package application.notes.spellcheck.raw;

import rita.RiTa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpellCheckerRaw {
    private List<String> wordsInLexicon;
    private List<String> wordsNotInLexicon;
    private String[] wordList;

    private SpellCheckerRaw(String[] wordListToCheckSpelling) {
        this.wordList = wordListToCheckSpelling;
    }

    public static SpellCheckerRaw initializeSpellCheckerRaw(String[] wordList){
        SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.wordsInLexicon = spellCheckerRaw.filterPositives(spellCheckerRaw.wordList);
        spellCheckerRaw.wordsNotInLexicon = spellCheckerRaw.filterNegatives(spellCheckerRaw.wordList);
        return spellCheckerRaw;
    }

    public List<String> filterNegatives(String[] words) {
        return Arrays.stream(words).filter(
                word -> !RiTa._lexicon().hasWord(word)).distinct().collect(Collectors.toList());
    }

    public List<String> filterPositives(String[] words) {
        return Arrays.stream(words).filter(
                word -> RiTa._lexicon().hasWord(word)).distinct().collect(Collectors.toList());
    }

    public long countWordsPresentInLexicon(String[] words) {
        return Arrays.stream(words).filter(word -> RiTa._lexicon().hasWord(word)).count();
    }

    public List<String> getWordsInLexicon() {
        return wordsInLexicon;
    }

    public List<String> getWordsNotInLexicon() {
        return wordsNotInLexicon;
    }
}
