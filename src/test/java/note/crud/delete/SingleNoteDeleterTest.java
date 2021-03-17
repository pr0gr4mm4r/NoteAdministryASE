package note.crud.delete;

import base.notes.crud.delete.single.SingleNoteDeleter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleNoteDeleterTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testSingleFileDeletion() throws IOException {
        File artificialFile = temporaryFolder.newFile("myfile.txt");
        assertTrue(artificialFile.exists());
        SingleNoteDeleter singleNoteDeleter = new SingleNoteDeleter();
        singleNoteDeleter.deleteSingleNote("myfile.txt", temporaryFolder.getRoot().getPath());
        assertFalse(artificialFile.exists());
    }
}
