package unittests.notes.crud.declare;

import application.notes.crud.declare.single.NoteDeclaratorFake;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;


import static application.notes.crud.declare.single.NoteDeclaratorFake.initializeFakeNoteDeclarator;
import static java.util.Arrays.*;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.*;

import static utility.strings.RandomStringCreator.*;

public class NoteDeclaratorTest {
    private NoteDeclaratorFake noteDeclaratorFake;
    private String randomNoteName;

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void createNoteDeclaratorFake() {
        randomNoteName = createRandomString();
        noteDeclaratorFake = initializeFakeNoteDeclarator(randomNoteName, temporaryFolder.getRoot().getPath() + "\\");
    }

    @Test
    public void checkNoteNameCorrectlySet() {
        final String noteName = noteDeclaratorFake.getNoteName();
        assertEquals(noteName, randomNoteName);
    }

    @Test
    public void declareNoteTest() throws IOException {
        noteDeclaratorFake.declareNote(noteDeclaratorFake.getPathToNote());
        assertEquals(1, asList(requireNonNull(temporaryFolder.getRoot().list())).size());
    }
}
