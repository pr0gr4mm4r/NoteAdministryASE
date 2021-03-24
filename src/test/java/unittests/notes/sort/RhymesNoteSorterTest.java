package unittests.notes.sort;

import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.processors.single.SingleNoteProcessor;
import application.notes.sort.sorter.RhymesNoteSorter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static unittests.notes.crud.read.NoteReaderTest.writingTestContentToArtificialFile;

public class RhymesNoteSorterTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void increaseCounterForEachRhymeTest() throws IOException {
      /*  File file1 = temporaryFolder.newFile("myfile1.txt");
        File file2 = temporaryFolder.newFile("myfile2.txt");
        String testContent = "fine line whine";
        String testContent2 = "I like flowers";
        writingTestContentToArtificialFile(file1, testContent);
        writingTestContentToArtificialFile(file2, testContent2);
        List<Entry<String, Integer>> wordsInLexiconEntries = new ArrayList<>();
        int counter = 0;
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        RhymesNoteSorter rhymesNoteSorter = new RhymesNoteSorter(multiNoteProcessor);*/
    }
}
