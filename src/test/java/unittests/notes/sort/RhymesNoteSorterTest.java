package unittests.notes.sort;

import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;

public class RhymesNoteSorterTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void increaseCounterForEachRhymeTest() throws IOException {
        Set<Entry<String, Boolean>> wordsInLexiconEntries;
        Map<String, Boolean> map = new HashMap<>();
        map.put("fine", true);
        map.put("line", true);
        map.put("whine", true);
        wordsInLexiconEntries = map.entrySet();
        RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(new MultiNoteProcessor());
        List<Entry<String, Boolean>> wordsInLexiconEntryList = new ArrayList<>(wordsInLexiconEntries);
        int counter = 0;
       /* counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);
        counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);
        counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);*/
        assertEquals(3, counter);
    }
}
