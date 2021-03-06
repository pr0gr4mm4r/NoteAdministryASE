package note;

import base.config.Globals;
import base.notes.crud.declare.NoteDeclarator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoteDeclaratorTest {
    private NoteDeclarator noteDeclarator;

    @BeforeEach
    public void createNoteDeclarator() {
        noteDeclarator = new NoteDeclarator();
    }

    @Test
    public void scanner() {
        Scanner scanner = noteDeclarator.getScanner();
        assertEquals(scanner.getClass(), Scanner.class);
    }

    @Test
    public void noteName() {
        String noteName = noteDeclarator.getNoteName();
        assertEquals(noteName, "testString");
    }

    @Test
    public void path() {
        String path = Globals.path_for_notes;
        assertEquals(path, "src/main/java/base/files/");
    }
}
