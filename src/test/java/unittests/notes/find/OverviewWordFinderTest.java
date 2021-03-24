package unittests.notes.find;

import application.notes.find.multi.OverviewWordFinder;
import application.notes.processors.multi.MultiNoteProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static unittests.notes.crud.read.NoteReaderTest.writingTestContentToArtificialFile;
import static org.junit.Assert.*;


public class OverviewWordFinderTest {
    private OverviewWordFinder overviewWordFinder;
    private File artificialFile;
    private final String testWord = "test";

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        artificialFile = temporaryFolder.newFile("myfile.txt");
        overviewWordFinder = new OverviewWordFinder(testWord, new MultiNoteProcessor(temporaryFolder.getRoot().getPath() + "\\"));
    }

    @Test
    public void createNoteListTest() {
        List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get("testPath"));
        List<String> noteList = overviewWordFinder.createNoteList(pathList);
        assertTrue("NoteList not initialized successfully", noteList.size() > 0);
    }

    @Test
    public void checkIfKeyWordIsPresent() {
        List<Map<Integer, Integer>> testList = new ArrayList<>();
        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(1, 0);
        testMap.put(2, 0);
        testMap.put(3, 0);
        testList.add(testMap);
        assertFalse(overviewWordFinder.checkIfKeyWordIsPresent(testList));
        testMap.put(1, 1);
        testMap.put(2, 5);
        testMap.put(3, 0);
        assertTrue(overviewWordFinder.checkIfKeyWordIsPresent(testList));
    }

    @Test
    public void createRememberRemovedList() {
        List<Map<Integer, Integer>> testList = new ArrayList<>();
        Map<Integer, Integer> testMap1 = new HashMap<>();
        Map<Integer, Integer> testMap2 = new HashMap<>();
        testMap1.put(1, 0);
        testMap1.put(2, 0);
        testMap1.put(3, 0);
        testMap2.put(1,1);
        testMap2.put(2,0);
        testMap2.put(3,4);
        testList.add(testMap1);
        testList.add(testMap2);
        List<Integer> testRememberRemovedList = overviewWordFinder.createRememberRemovedList(testList);
        assertEquals(1, testRememberRemovedList.size());
    }


    @Test
    public void composeWordOccurrenceSingleNoteTest() throws IOException {
        final Path artificialFilePath = artificialFile.toPath();
        writingTestContentToArtificialFile(artificialFile, testWord + " " + testWord + "\n" + testWord);
        Map<Integer, Integer> wordOccurrence = overviewWordFinder.composeWordOccurrenceSingleNote(artificialFilePath);
        int occurrenceInFirstLine = wordOccurrence.get(1);
        int occurrenceInSecondLine = wordOccurrence.get(2);
        Object nullExpectation = wordOccurrence.get(3);
        assertEquals(2, occurrenceInFirstLine);
        assertEquals(1, occurrenceInSecondLine);
        assertNull(nullExpectation);
    }
}
