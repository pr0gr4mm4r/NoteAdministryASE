package base.notes.crud.declare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static base.config.Globals.path;
import static base.config.Globals.scanner;
import static base.start.NoteAdministryStart.programRun;

public class NoteDeclarator {
    private String noteName;

    public NoteDeclarator() {
        if (programRun) {
            System.out.println("Please provide a name for the note:");
        }
        this.noteName = scanner.nextLine();
        final Path newFilePath = createCompletePath(noteName);
        declareNote(newFilePath);
    }

    public void declareNote(Path completePath) {
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

    public boolean tryToCreateFile(Path completePath) {
        try {
            Files.createFile(completePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addHeader(Path completePath) {
        String time = createCurrentTimeString();
        String header = this.noteName + " " + time;
        byte[] bytes = header.getBytes();
        try {
            Files.write(completePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDummyLines(final Path completePath) {
        String dummyMessage = "\n\nDUMMY TEXT";
        try {
            Files.write(
                    Paths.get(String.valueOf(completePath)),
                    dummyMessage.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createCurrentTimeString() {
        String rawDateString = Instant.now().plus(2, ChronoUnit.HOURS).toString();
        return rawDateString.replace("T", " ").substring(0, rawDateString.length() - 11);
    }

    public static Path createCompletePath(String noteName) {
        return Paths.get(path + noteName);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }}

