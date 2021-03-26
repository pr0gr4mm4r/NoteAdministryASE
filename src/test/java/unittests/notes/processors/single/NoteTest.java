package unittests.notes.processors.single;

import org.junit.Test;

import static application.notes.processors.single.Note.*;
import static org.junit.Assert.assertEquals;

public class NoteTest {
    @Test
    public void removeEmptyLinesTest() {
        String[] lines = {"test", "test", "", "test", "", "", "test"};
        lines = removeEmptyLines(lines);
        assertEquals(4, lines.length);
    }
}
