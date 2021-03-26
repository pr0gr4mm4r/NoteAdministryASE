package unittests.notes.find;

import application.notes.find.multi.OverviewWordFinder;
import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static application.notes.processors.multi.NoteStack.initializeNoteStack;
import static org.junit.Assert.*;
import static unittests.notes.helper.FileWriter.writingTestContentToArtificialFile;


public class OverviewWordFinderTest {
    private OverviewWordFinder overviewWordFinder;
    private File artificialFile;
    private final String testWord = "test";

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException, NoFilesInDirectoryException {
        artificialFile = temporaryFolder.newFile("myfile.txt");
        final NoteStack noteStack = initializeNoteStack(temporaryFolder.getRoot().getPath() + "\\");
        overviewWordFinder = new OverviewWordFinder(testWord, noteStack);
    }

    @Test
    public void createNoteListTest() {
        final List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get("testPath"));
        final List<String> noteList = overviewWordFinder.createNoteList(pathList);
        assertFalse("NoteList not initialized successfully", noteList.isEmpty());
    }

    @Test
    public void checkIfKeyWordIsPresent() {
        final List<Map<Integer, Integer>> testList = new ArrayList<>();
        final Map<Integer, Integer> testMap = new HashMap<>();
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
        final List<Map<Integer, Integer>> testList = new ArrayList<>();
        final Map<Integer, Integer> testMap1 = new HashMap<>();
        final Map<Integer, Integer> testMap2 = new HashMap<>();
        testMap1.put(1, 0);
        testMap1.put(2, 0);
        testMap1.put(3, 0);
        testMap2.put(1,1);
        testMap2.put(2,0);
        testMap2.put(3,4);
        testList.add(testMap1);
        testList.add(testMap2);
        final List<Integer> testRememberRemovedList = overviewWordFinder.createRememberRemovedList(testList);
        assertEquals(1, testRememberRemovedList.size());
    }


    @Test
    public void composeWordOccurrenceSingleNoteTest() throws IOException {
        final Path artificialFilePath = artificialFile.toPath();
        writingTestContentToArtificialFile(artificialFile, testWord + " " + testWord + "\n" + testWord);
        final Map<Integer, Integer> wordOccurrence = overviewWordFinder.composeWordOccurrenceSingleNote(artificialFilePath);
        final int occurrenceInFirstLine = wordOccurrence.get(1);
        final int occurrenceInSecondLine = wordOccurrence.get(2);
        final Object nullExpectation = wordOccurrence.get(3);
        assertEquals(2, occurrenceInFirstLine);
        assertEquals(1, occurrenceInSecondLine);
        assertNull(nullExpectation);
    }
}
