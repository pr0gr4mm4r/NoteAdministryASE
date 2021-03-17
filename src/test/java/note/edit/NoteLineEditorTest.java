package note.edit;

import base.notes.crud.read.single.NoteReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class NoteLineEditorTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testEditNoteMethod() throws IOException {
        final String testContent = "testContent";
        final File createdFile = temporaryFolder.newFile("myfile.txt");
        Path completePath = createdFile.toPath();
        String fileContent = NoteReader.readNote(completePath);
        assertEquals(fileContent, "testContent");
    }
}
