package integrationtests.sort;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static application.notes.processors.multi.NoteStack.*;
import static org.junit.Assert.assertEquals;
import static unittests.notes.helper.FileWriter.writingTestContentToArtificialFile;

public class RhymesNoteSorterIntegrationTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void correctInitializationTest() throws IOException, NoFilesInDirectoryException {
        File file1 = temporaryFolder.newFile("myfile1.txt");
        File file2 = temporaryFolder.newFile("myfile2.txt");
        String testContent = "fine line whine";
        String testContent2 = "I like flowers";
        writingTestContentToArtificialFile(file1, testContent);
        writingTestContentToArtificialFile(file2, testContent2);
        NoteStack noteStack = initializeNoteStack(file1.getParent() + "\\");
        RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(noteStack);
        Map<String, Integer> map = rhymesNoteSorter.initialize();
        int rhymeCount1 = map.get("myfile1.txt");
        int rhymeCount2 = map.get("myfile2.txt");
        assertEquals("Rhymes were not counted correctly", 3, rhymeCount1);
        assertEquals("Rhymes werde not counted correctly", 0, rhymeCount2);
    }
}
