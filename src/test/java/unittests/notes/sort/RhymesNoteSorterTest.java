package unittests.notes.sort;

import application.notes.processors.multi.NoteStack;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RhymesNoteSorterTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void increaseCounterForEachRhymeTest() throws IOException {
        RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(new NoteStack());
        List<String> wordsInLexiconEntryList = new ArrayList<>();
        wordsInLexiconEntryList.add("test");
        wordsInLexiconEntryList.add("quest");
        wordsInLexiconEntryList.add("chest");
        int counter = 0;

        counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);

        assertEquals(3, counter);

        counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);
        counter = rhymesNoteSorter.increaseCounterForEachRhyme(wordsInLexiconEntryList, counter);

        assertEquals(9, counter);
    }
}
