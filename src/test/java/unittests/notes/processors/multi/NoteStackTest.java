package unittests.notes.processors.multi;

import application.notes.processors.multi.NoteStack;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NoteStackTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void listNoteNamesTest() throws IOException {
        temporaryFolder.newFile("myfile.txt");
        final String path = temporaryFolder.getRoot().getPath();
        final NoteStack multiNoteProcessor = new NoteStack();
        final Set<String> noteNames =  multiNoteProcessor.listNoteNames(path,1);
        final List<String> noteNameList = new ArrayList<>(noteNames);
        assertEquals("myfile.txt", noteNameList.get(0));
    }

}
