package note.processors.single;

import base.notes.processors.single.SingleNoteProcessor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleNoteProcessorTest {
    @Test
    public void removeEmptyLinesTest() {
        SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();
        String[] lines = {"test", "test", "", "test", "", "", "test"};
        lines = singleNoteProcessor.removeEmptyLines(lines);
        assertEquals(4, lines.length);
    }
}
