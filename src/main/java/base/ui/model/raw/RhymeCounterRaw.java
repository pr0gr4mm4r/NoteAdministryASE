package base.ui.model.raw;

import base.WordExistenceMap;
import rita.RiTa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static base.notes.spellcheck.raw.SpellCheckerRaw.filterNegatives;
import static base.notes.spellcheck.raw.SpellCheckerRaw.filterPositives;

public class RhymeCounterRaw {
    private String content;

    public RhymeCounterRaw(String content) {
        this.content = content;
    }

    public Integer calcRhymes(){
        int counter = 0;
        String[] splittedContent = content.split("\\s+");
        List<String> wordsInLexicon = filterPositives(splittedContent);
        List<String> wordsNotInLexicon = filterNegatives(splittedContent);
        WordExistenceMap wordExistenceModel = new WordExistenceMap();
        wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
        List<Map.Entry<String, Boolean>> wordsInLexiconEntries = wordExistenceModel.entrySet().stream().
                filter(Map.Entry::getValue).collect(Collectors.toList());
        final int size = wordsInLexiconEntries.size();
        for (int j = 0; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                String firstWord = wordsInLexiconEntries.get(j).getKey();
                String secondWord = wordsInLexiconEntries.get(k).getKey();
                if (RiTa.isRhyme(firstWord, secondWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    }




