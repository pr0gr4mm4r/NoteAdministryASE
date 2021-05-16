package application.notes.crud.declare.single;

import application.notes.crud.declare.abstraction.FileCreator;
import application.notes.crud.declare.abstraction.HeaderAdder;
import application.notes.crud.declare.abstraction.HeaderInformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static config.Globals.path_for_notes;
import static utility.path.PathCreator.createCompletePath;


public class NoteDeclarator implements HeaderAdder, FileCreator {
    protected String noteName;
    protected Path pathToNote;

    protected NoteDeclarator() {

    }

    public static NoteDeclarator initializeNoteDeclarator(final String noteName) {
        NoteDeclarator noteDeclarator = new NoteDeclarator();
        noteDeclarator.noteName = noteName;
        noteDeclarator.pathToNote = createCompletePath(noteName, path_for_notes);
        return noteDeclarator;
    }

    public void declareNote(final Path completePath) throws IOException {
        final boolean noteDoesNotExist = tryToCreateFile(completePath);
        if (noteDoesNotExist) {
            HeaderInformation headerInformation = new HeaderInformation(completePath);
            addHeader(headerInformation);
            addDummyLines(completePath);
            printSuccessMessage();
        }
    }

    protected void printSuccessMessage() {
        System.out.println("Creation of note was successful");
    }

    @Override
    public boolean tryToCreateFile(final Path completePath) throws IOException {
        Files.createFile(completePath);
        return true;
    }
//Trennnung Persistenz domain
    @Override
    public void addHeader(final HeaderInformation headerInformation) throws IOException {
        final String time = createCurrentTimeString();
        final String header = this.noteName + " " + time;
        final byte[] bytes = header.getBytes();
        Files.write(headerInformation.getPath(), bytes);
    }

    private void addDummyLines(final Path completePath) throws IOException {
        final String dummyMessage = "\n\nDUMMY TEXT";
        Files.write(
                Paths.get(String.valueOf(completePath)),
                dummyMessage.getBytes(),
                StandardOpenOption.APPEND);
    }

    public static String createCurrentTimeString() {
        final String rawDateString = Instant.now().plus(2, ChronoUnit.HOURS).toString();
        return rawDateString.replace("T", " ").substring(0, rawDateString.length() - 11);
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(final String noteName) {
        this.noteName = noteName;
    }

    public Path getPathToNote() {
        return pathToNote;
    }
}

