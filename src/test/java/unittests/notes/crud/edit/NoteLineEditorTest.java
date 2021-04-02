package unittests.notes.crud.edit;

import application.notes.crud.edit.model.LineOverwriterInformation;
import application.notes.crud.edit.single.NoteLineEditor;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testhelper.FileWriter.writingTestContentToArtificialFile;

public class NoteLineEditorTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void overwriteLineMethod() throws IOException {
        final File artificialFile = temporaryFolder.newFile("myfile.txt");
        final Path artificialFilePath = artificialFile.toPath();
        assertTrue("artificial file was not created successfully", artificialFile.exists());
        writingTestContentToArtificialFile(artificialFile, "test\ntest\ntest");
        final NoteLineEditor noteLineEditor = new NoteLineEditor();
        final LineOverwriterInformation lineOverwriterInformation = new LineOverwriterInformation.Builder()
                .path(artificialFilePath)
                .indexLineNumber(1)
                .replacementLine("replacement as one likes")
                .build();
        noteLineEditor.overwriteLine(lineOverwriterInformation);
        final List<String> noteList = Files.readAllLines(artificialFilePath);
        final String line1 = noteList.get(0);
        final String line2 = noteList.get(1);
        final String line3 = noteList.get(2);
        assertEquals("Unwanted Side Effects occurred", "test", line1);
        assertEquals("Line did not change accordingly", "replacement as one likes", line2);
        assertEquals("Unwanted Side Effects occurred", "test", line3);
    }

    @Test
    public void noteHasEnoughLinesMethodPositive() throws IOException {
        final File artificialFile = temporaryFolder.newFile("myfile.txt");
        final Path artificialFilePath = artificialFile.toPath();
        assertTrue("artificial file was not created successfully", artificialFile.exists());
        writingTestContentToArtificialFile(artificialFile, "test\ntest\ntest");
        final NoteLineEditor noteLineEditor = new NoteLineEditor();
        final boolean positive1 = noteLineEditor.noteHasEnoughLines(artificialFilePath, 2);
        final boolean positive2 = noteLineEditor.noteHasEnoughLines(artificialFilePath, 1);
        final boolean positive3 = noteLineEditor.noteHasEnoughLines(artificialFilePath, 3);
        assertTrue(positive1);
        assertTrue(positive2);
        assertTrue(positive3);
    }

    @Test
    public void noteHasEnoughLinesMethodNegative() throws IOException {
        final File artificialFile = temporaryFolder.newFile("myfile.txt");
        final Path artificialFilePath = artificialFile.toPath();
        assertTrue("artificial file was not created successfully", artificialFile.exists());
        writingTestContentToArtificialFile(artificialFile, "test\ntest\ntest");
        final NoteLineEditor noteLineEditor = new NoteLineEditor();
        final List<Boolean> negativeExpected = new ArrayList<>();
        negativeExpected.add(noteLineEditor.noteHasEnoughLines(artificialFilePath, -10));
        negativeExpected.add(noteLineEditor.noteHasEnoughLines(artificialFilePath, 0));
        negativeExpected.add(noteLineEditor.noteHasEnoughLines(artificialFilePath, 4));
        negativeExpected.add(noteLineEditor.noteHasEnoughLines(artificialFilePath, 5));
        negativeExpected.add(noteLineEditor.noteHasEnoughLines(artificialFilePath, 20));
        negativeExpected.forEach(Assert::assertFalse);
    }


}
