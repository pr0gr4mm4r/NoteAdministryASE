package unittests.notes.crud.delete;

import application.notes.crud.delete.single.SingleNoteDeleter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleNoteDeleterTest {

    @Rule
    private final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testSingleFileDeletion() throws IOException {
        final File artificialFile = temporaryFolder.newFile("myfile.txt");
        final SingleNoteDeleter singleNoteDeleter = new SingleNoteDeleter();

        singleNoteDeleter.deleteSingleNote("myfile.txt", temporaryFolder.getRoot().getPath());

        assertTrue("artificial file was not created successfully", artificialFile.exists());
        assertFalse("deletion of artificial file did not work", artificialFile.exists());
    }
}
