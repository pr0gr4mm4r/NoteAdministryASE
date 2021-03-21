package note.processors.multi;

import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.processors.single.SingleNoteProcessor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public void listNoteNamesTest() throws IOException {
        temporaryFolder.newFile("myfile.txt");
        String path = temporaryFolder.getRoot().getPath();
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        Set<String> noteNames =  multiNoteProcessor.listNoteNames(path,1);
        List<String> noteNameList = new ArrayList<>(noteNames);
        assertEquals("myfile.txt", noteNameList.get(0));
    }

}
