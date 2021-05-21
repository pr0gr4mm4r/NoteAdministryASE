package unittests.notes.counter;

import application.notes.ui.raw.RhymeCounterRaw;
import org.junit.Test;
import utility.map.WordExistenceMap;

import java.util.List;
import java.util.Map.Entry;

import static org.junit.Assert.*;
import static utility.map.WordExistenceMap.initializeWordExistenceMap;

public class RhymeCounterRawTest {

    @Test
    public void customInitializeWordExistence() {
        RhymeCounterRaw rhymeCounterRaw = new RhymeCounterRaw("This\nis test\ncontent gonne wronng");
        String wrongFillMessage = "Map either not filled at all or with wrong keys";

        WordExistenceMap wordExistenceMap = rhymeCounterRaw.customInitializeWordExistence();

        assertEquals("Size of Map is not as expected", 6, wordExistenceMap.size());
        assertTrue(wrongFillMessage, wordExistenceMap.get("This"));
        assertTrue(wrongFillMessage, wordExistenceMap.get("is"));
        assertTrue(wrongFillMessage, wordExistenceMap.get("test"));
        assertTrue(wrongFillMessage, wordExistenceMap.get("content"));
        assertFalse(wrongFillMessage, wordExistenceMap.get("gonne"));
        assertFalse(wrongFillMessage, wordExistenceMap.get("wronng"));
    }

    @Test
    public void initializeWordsInLexiconEntriesTest() {
        List<String> positives = List.of("positive1", "positive2");
        List<String> negatives = List.of("negative1", "negative2");
        WordExistenceMap wordExistenceMap = initializeWordExistenceMap(positives, negatives);
        RhymeCounterRaw rhymeCounterRaw = new RhymeCounterRaw("This\nis test\ncontent");

        List<Entry<String, Boolean>> wordsInLexicon = rhymeCounterRaw.initializeWordsInLexiconEntries(wordExistenceMap);

        for (Entry<String, Boolean> entry : wordsInLexicon) {
            assertTrue(entry.getValue());
        }
        assertEquals(2, wordsInLexicon.size());
    }
}
