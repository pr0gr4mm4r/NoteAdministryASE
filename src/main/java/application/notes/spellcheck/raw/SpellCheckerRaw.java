package application.notes.spellcheck.raw;

import rita.RiTa;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpellCheckerRaw {
    private List<String> wordsInLexicon;
    private List<String> wordsNotInLexicon;
    private String[] wordList;

    private SpellCheckerRaw(final String... wordListToCheckSpelling) {
        this.wordList = wordListToCheckSpelling;
    }

    public static SpellCheckerRaw initializeSpellCheckerRaw(final String... wordList){
        final SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
        spellCheckerRaw.wordsInLexicon = spellCheckerRaw.filterPositives(spellCheckerRaw.wordList);
        spellCheckerRaw.wordsNotInLexicon = spellCheckerRaw.filterNegatives(spellCheckerRaw.wordList);
        return spellCheckerRaw;
    }

    public List<String> filterNegatives(final String... words) {
        return Arrays.stream(words).filter(
                word -> !RiTa._lexicon().hasWord(word)).distinct().collect(Collectors.toList());
    }

    public List<String> filterPositives(final String... words) {
        return Arrays.stream(words).filter(
                word -> RiTa._lexicon().hasWord(word)).distinct().collect(Collectors.toList());
    }

    public long countWordsPresentInLexicon(final String... words) {
        return Arrays.stream(words).filter(word -> RiTa._lexicon().hasWord(word)).count();
    }

    public List<String> getWordsInLexicon() {
        return wordsInLexicon;
    }

    public List<String> getWordsNotInLexicon() {
        return wordsNotInLexicon;
    }
}
