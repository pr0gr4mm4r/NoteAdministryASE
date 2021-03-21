package note.find;

import base.notes.find.multi.OverviewWordFinder;
import base.notes.processors.multi.MultiNoteProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static note.crud.read.NoteReaderTest.writingTestContentToFile;
import static org.junit.Assert.*;


public class OverviewWordFinderTest {
    private OverviewWordFinder overviewWordFinder;
    private File artificialFile;

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        artificialFile = temporaryFolder.newFile("myfile.txt");
        overviewWordFinder = new OverviewWordFinder("test", new MultiNoteProcessor(temporaryFolder.getRoot().getPath() + "\\"));
    }

    @Test
    public void createNoteListTest() {
        List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get("testPath"));
        List<String> noteList = overviewWordFinder.createNoteList(pathList);
        assertTrue("NoteList not initialized successfully",noteList.size() > 0);
    }

    @Test
    public void test() {

    }

    @Test
    public void composeWordOccurenceSingleNoteTest() throws IOException {
        final Path artificialFilePath = artificialFile.toPath();
        writingTestContentToFile(artificialFile, "test\ntest\ntest");
        Map<Integer,Integer> wordOccurrence = overviewWordFinder.composeWordOccurenceSingleNote(artificialFilePath);
        assertEquals(1, (int) wordOccurrence.get(1));
        assertEquals(1, (int) wordOccurrence.get(2));
        assertEquals(1, (int) wordOccurrence.get(3));
        assertNull(wordOccurrence.get(4));
    }
}
