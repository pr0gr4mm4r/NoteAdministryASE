package integrationtests.sort;

import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static unittests.notes.crud.read.NoteReaderTest.writingTestContentToArtificialFile;
import static org.junit.Assert.assertEquals;

public class RhymesNoteSorterIntegrationTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void correctInitializationTest() throws IOException {
        File file1 = temporaryFolder.newFile("myfile1.txt");
        File file2 = temporaryFolder.newFile("myfile2.txt");
        String testContent = "fine line whine";
        String testContent2 = "I like flowers";
        writingTestContentToArtificialFile(file1, testContent);
        writingTestContentToArtificialFile(file2, testContent2);
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor(file1.getParent() + "\\");
        RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(multiNoteProcessor);
        Map<String, Integer> map = rhymesNoteSorter.initialize();
        int rhymeCount1 = map.get("myfile1.txt");
        int rhymeCount2 = map.get("myfile2.txt");
        assertEquals("Rhymes were not counted correctly", 3, rhymeCount1);
        assertEquals("Rhymes werde not counted correctly", 0, rhymeCount2);
    }
}
