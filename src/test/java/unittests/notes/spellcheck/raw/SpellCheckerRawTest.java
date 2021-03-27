package unittests.notes.spellcheck.raw;

import application.notes.spellcheck.raw.SpellCheckerRaw;
import org.junit.Before;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpellCheckerRawTest {
    private final String[] exampleStrings = {"falsee", "faaalse", "correct"};
    private SpellCheckerRaw spellCheckerRaw;

    @Before
    public void setup() {
        spellCheckerRaw = SpellCheckerRaw.initializeSpellCheckerRaw(exampleStrings);
    }

    @Test
    public void testCorrectFillOfWordsInLexicon() {
        final ArrayList exampleStringListPositives = new ArrayList(spellCheckerRaw.getWordsInLexicon());

        assertTrue(exampleStringListPositives.contains("correct"));
        assertFalse(exampleStringListPositives.contains("falsee"));
        assertFalse(exampleStringListPositives.contains("faaalse"));
    }

    @Test
    public void testCorrectFillOfWordsNotInLexicon() {
        final ArrayList exampleStringListNegatives = new ArrayList(spellCheckerRaw.getWordsNotInLexicon());

        assertTrue(exampleStringListNegatives.contains("falsee"));
        assertTrue(exampleStringListNegatives.contains("faaalse"));
        assertFalse(exampleStringListNegatives.contains("correct"));
    }

    @Test
    public void countWordsPresentInLexiconTest() {
        final double wordsInLexikon = spellCheckerRaw.countWordsPresentInLexicon(exampleStrings);

        assertEquals(1, wordsInLexikon, 0.0);
    }


}
