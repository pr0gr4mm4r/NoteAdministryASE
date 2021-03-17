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
    public void addAndDeleteFileFromTemporaryFolder() throws IOException {
        File createdFile = temporaryFolder.newFile("myfile.txt");
        assertTrue(createdFile.exists());
        SingleNoteDeleter singleNoteDeleter = new SingleNoteDeleter();
        singleNoteDeleter.deleteSingleNote("myfile.txt", temporaryFolder.getRoot().getPath());
        assertFalse(createdFile.exists());
    }
}
