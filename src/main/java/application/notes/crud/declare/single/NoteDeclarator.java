package application.notes.crud.declare.single;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class NoteDeclarator {
    private String noteName;
    private Path pathToNote;

    public NoteDeclarator(final Path pathToNote, final String noteName) {
        this.noteName = noteName;
        this.pathToNote = pathToNote;
    }

    void declareNote(final Path completePath) throws IOException {
        final boolean noteDoesNotExist = tryToCreateFile(completePath);
        if (noteDoesNotExist) {
            addHeader(completePath);
            addDummyLines(completePath);
            printSuccessMessage();
        }
    }

    private void printSuccessMessage() {
        System.out.println("Creation of note was successful");
    }

    private boolean tryToCreateFile(final Path completePath) throws IOException {

        Files.createFile(completePath);
        return true;
    }

    private void addHeader(final Path completePath) throws IOException {
        final String time = createCurrentTimeString();
        final String header = this.noteName + " " + time;
        final byte[] bytes = header.getBytes();
        Files.write(completePath, bytes);
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

