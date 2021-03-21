package note.find;

import base.notes.find.single.SingleNoteWordFinder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SingleNoteWordFinderTest {
    private SingleNoteWordFinder singelNoteWordFinder;

    @Before
    public void setup(){
        singelNoteWordFinder = new SingleNoteWordFinder();
    }

    @Test
    public void filterWordOccurrenceTest() {
        Map<Integer, Integer> wordOccurrence = new HashMap<>();
        wordOccurrence.put(1,0);
        wordOccurrence.put(2,0);
        wordOccurrence.put(3,0);
        wordOccurrence.put(4,2);
        wordOccurrence.put(5,0);
        wordOccurrence = singelNoteWordFinder.filterWordOccurrence(wordOccurrence);
        assertEquals(1, wordOccurrence.size());
    }

    @Test
    public void bla() {

    }
}


