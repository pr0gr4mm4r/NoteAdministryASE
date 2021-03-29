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


public class NoteDeclarator implements HeaderAdder, FileCreator {
    private String noteName;
    private Path pathToNote;

    public NoteDeclarator(final Path pathToNote, final String noteName) {
        this.noteName = noteName;
        this.pathToNote = pathToNote;
    }

    void declareNote(final Path completePath) throws IOException {
        final boolean noteDoesNotExist = tryToCreateFile(completePath);
        if (noteDoesNotExist) {
            HeaderInformation headerInformation = new HeaderInformation(completePath);
            addHeader(headerInformation);
            addDummyLines(completePath);
            printSuccessMessage();
        }
    }

    private void printSuccessMessage() {
        System.out.println("Creation of note was successful");
    }

    @Override
    public boolean tryToCreateFile(final Path completePath) {
        try {
            Files.createFile(completePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

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

    public void setPathToNote(final Path pathToNote) {
        this.pathToNote = pathToNote;
    }
}

