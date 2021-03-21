package note.processors.multi;

import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.processors.single.SingleNoteProcessor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static base.config.Globals.path_for_notes;
import static org.junit.Assert.assertEquals;

public class MultiNoteProcessorTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Test
    public void removeEmptyLinesTest() {
        SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor();
        String[] lines = {"test", "test", "", "test", "", "", "test"};
        lines = singleNoteProcessor.removeEmptyLines(lines);
        assertEquals(4, lines.length);
    }

    @Test
    public void listNoteNamesTest() {
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
    }

}
