package integrationtests.notes.sort;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static application.notes.processors.multi.NoteStack.*;
import static org.junit.Assert.assertEquals;
import static testhelper.FileWriter.writingTestContentToArtificialFile;

public class RhymesNoteSorterIntegrationTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void correctInitializationTest() throws IOException, NoFilesInDirectoryException {
        final File file1 = temporaryFolder.newFile("myfile1.txt");
        final File file2 = temporaryFolder.newFile("myfile2.txt");
        final String testContent = "fine line whine";
        final String testContent2 = "I like flowers";
        writingTestContentToArtificialFile(file1, testContent);
        writingTestContentToArtificialFile(file2, testContent2);
        final NoteStack noteStack = initializeNoteStack(file1.getParent() + "\\");
        final RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(noteStack);
        final Map<String, Integer> map = rhymesNoteSorter.initialize();
        final int rhymeCount1 = map.get("myfile1.txt");
        final int rhymeCount2 = map.get("myfile2.txt");
        assertEquals("Rhymes were not counted correctly", 3, rhymeCount1);
        assertEquals("Rhymes werde not counted correctly", 0, rhymeCount2);
    }
}
