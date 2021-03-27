package unittests.notes.processors.multi;

import application.notes.processors.multi.NoteStack;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class NoteStackTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void listNoteNamesTest() throws IOException {
        temporaryFolder.newFile("myfile.txt");
        final String path = temporaryFolder.getRoot().getPath();
        final NoteStack noteStack = new NoteStack();
        final Set<String> noteNames =  noteStack.listNoteNames(path,1);
        final List<String> noteNameList = new ArrayList<>(noteNames);
        assertEquals("myfile.txt", noteNameList.get(0));
        assertEquals(1, noteNameList.size());
    }

    @Test
    public void listNoteNamesExtendedTest() throws IOException {
        temporaryFolder.newFile("myfile1.txt");
        temporaryFolder.newFile("myfile2.txt");
        temporaryFolder.newFile("myfile3.txt");
        final String path = temporaryFolder.getRoot().getPath();
        final NoteStack noteStack = new NoteStack();
        final Set<String> noteNames =  noteStack.listNoteNames(path,1);
        final List<String> noteNameList = new ArrayList<>(noteNames);
        assertTrue(noteNameList.contains("myfile1.txt"));
        assertTrue(noteNameList.contains("myfile2.txt"));
        assertTrue(noteNameList.contains("myfile3.txt"));
        assertEquals(3, noteNameList.size());
    }

}
