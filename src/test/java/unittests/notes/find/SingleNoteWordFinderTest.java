package unittests.notes.find;

import application.notes.find.single.SingleNoteWordFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SingleNoteWordFinderTest {
    private SingleNoteWordFinder singelNoteWordFinder;

    @Before
    public void setup() {
        singelNoteWordFinder = new SingleNoteWordFinder();
    }

    @Test
    public void filterWordOccurrenceTest() {
        Map<Integer, Integer> wordOccurrence = new HashMap<>();
        wordOccurrence.put(1, 0);
        wordOccurrence.put(2, 0);
        wordOccurrence.put(3, 0);
        wordOccurrence.put(4, 2);
        wordOccurrence.put(5, 0);

        wordOccurrence = singelNoteWordFinder.filterWordOccurrence(wordOccurrence);

        assertEquals(wordOccurrence.get(4).intValue(), 2);

        wordOccurrence.remove(4);

        assertTrue(wordOccurrence.isEmpty());
    }

    @Test
    public void countOccurrenceOfWordTest() {
        int occurrence = singelNoteWordFinder.countOccurrenceOfWord("test", "test test test test");

        assertEquals(4, occurrence);

        occurrence = singelNoteWordFinder.countOccurrenceOfWord("test", "testtestrgergergerregerr");

        assertEquals(2, occurrence);

        occurrence = singelNoteWordFinder.countOccurrenceOfWord("test", "testestrgergetestrregerr");

        assertEquals(2, occurrence);
    }
}


