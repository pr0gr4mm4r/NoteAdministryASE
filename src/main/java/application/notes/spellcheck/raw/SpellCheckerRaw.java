package application.notes.spellcheck.raw;

import rita.RiTa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpellCheckerRaw {
    private List<String> wordsInLexicon = new ArrayList<>();
    private List<String> wordsNotInLexicon = new ArrayList<>();
    private String[] wordList;

    public SpellCheckerRaw(String[] wordListToCheckSpelling) {
        this.wordList = wordListToCheckSpelling;
    }

    public void checkSpelling(String[] wordList) {
        wordsInLexicon = filterPositives(wordList);
        wordsNotInLexicon = filterNegatives(wordList);
    }

    public static List<String> filterNegatives(String[] words) {
        return Arrays.stream(words).filter(
                word -> !RiTa._lexicon().hasWord(word)).distinct().collect(Collectors.toList());
    }

    public static List<String> filterPositives(String[] words) {
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

    public void setWordsInLexicon(List<String> wordsInLexicon) {
        this.wordsInLexicon = wordsInLexicon;
    }

    public void setWordsNotInLexicon(List<String> wordsNotInLexicon) {
        this.wordsNotInLexicon = wordsNotInLexicon;
    }

    public String[] getWordListToCheckSpelling() {
        return wordList;
    }

    public void setWordListToCheckSpelling(String[] wordListToCheckSpelling) {
        this.wordList = wordListToCheckSpelling;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
}
