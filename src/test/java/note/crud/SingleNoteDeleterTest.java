package note.crud;

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
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFile = folder.newFile("myfile.txt");
        assertTrue(createdFile.exists());
        SingleNoteDeleter singleNoteDeleter = new SingleNoteDeleter();
        singleNoteDeleter.deleteSingleNote("myfile.txt", folder.getRoot().getPath());
        assertFalse(createdFile.exists());
    }
}
