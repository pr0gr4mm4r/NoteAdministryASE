package note.crud;

import base.notes.crud.declare.single.NoteDeclarator;
import base.notes.crud.delete.single.SingleNoteDeleter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Scanner;

import static base.config.Globals.*;
import static base.config.Globals.scanner;
import static base.notes.crud.declare.caller.NoteDeclaratorCaller.createCompletePath;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoteDeclaratorTest {
    private NoteDeclarator noteDeclarator;
    private String generatedString;

    @BeforeEach
    public void createNoteDeclarator() {
        generatedString = RandomStringUtils.randomAlphabetic(6);
        Path path = createCompletePath(generatedString);
        noteDeclarator = new NoteDeclarator(path, generatedString);
    }

    @Test
    public void scanner() {
        assertEquals(scanner.getClass(), Scanner.class);
    }

    @Test
    public void noteName() {
        String noteName = noteDeclarator.getNoteName();
        assertEquals(noteName, generatedString);
    }

    @Test
    public void path() {
        assertEquals(path_for_notes, "src/main/java/base/files/notes/");
    }

    @AfterEach
    public void deleteDeclaratedNote() {
        new SingleNoteDeleter(generatedString);
    }
}
