package unittests.notes.crud.declare;

import application.notes.crud.declare.single.NoteDeclarator;
import application.notes.crud.delete.single.SingleNoteDeleter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Scanner;

import static utility.path.PathCreator.createCompletePath;
import static config.Globals.*;
import static config.Globals.scanner;

import static org.junit.Assert.assertEquals;

public class NoteDeclaratorTest {
    private NoteDeclarator noteDeclarator;
    private String generatedString;

    @Before
    public void createNoteDeclarator() {
        generatedString = RandomStringUtils.randomAlphabetic(6);
        final Path path = createCompletePath(generatedString, path_for_notes);
        noteDeclarator = new NoteDeclarator(path, generatedString);
    }

    @Test
    public void scanner() {
        assertEquals(scanner.getClass(), Scanner.class);
    }

    @Test
    public void noteName() {
        final String noteName = noteDeclarator.getNoteName();
        assertEquals(noteName, generatedString);
    }

    @Test
    public void testPath() {
        assertEquals(path_for_notes, "src/main/java/base/files/notes/");
    }

    @After
    public void deleteDeclaratedNote() {
        new SingleNoteDeleter();
    }
}
