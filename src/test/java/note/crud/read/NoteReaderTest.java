package note.crud.read;

import base.notes.crud.read.single.NoteReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class NoteReaderTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testReadNoteMethod() throws IOException {
        final String testContent = "testContent\ntest\ntest";
        final File createdFile = temporaryFolder.newFile("myfile.txt");
        writingTestContentToFile(createdFile, testContent);
        Path completePath = createdFile.toPath();
        String fileContent = NoteReader.readNote(completePath);
        assertEquals("fileContent does not match the actual content of file",
                fileContent, "testContent\ntest\ntest");
    }

    @Test
    public void testReadNoteForFurtherProcessingMethod() throws IOException {
        final String testContent = "testContent\ntest\ntest";
        final File createdFile = temporaryFolder.newFile("myfile.txt");
        writingTestContentToFile(createdFile, testContent);
        Path completePath = createdFile.toPath();
        String fileContent = NoteReader.readNoteForNoteProcessing(completePath);
        assertEquals("fileContent does not match the actual content of file",
                fileContent, "testContent test test");
    }

    private void writingTestContentToFile(File createdFile, String testContent) {
        try (FileWriter fileWriter = new FileWriter(createdFile)) {
            fileWriter.write(testContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
