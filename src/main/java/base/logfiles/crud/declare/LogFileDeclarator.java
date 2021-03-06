package base.logfiles.crud.declare;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static base.config.Globals.path;
import static base.notes.crud.declare.NoteDeclarator.createCompletePath;
import static base.notes.crud.declare.NoteDeclarator.createCurrentTimeString;
import static base.notes.spellcheck.formatter.SingleNoteSpellCheckerResultFormatter.insertLineBreak;

public class LogFileDeclarator {
    private final String action;
    private final String logFileName;
    public LogFileDeclarator(String content, String action){
        this.action = action;
        logFileName = generateDefaultLogFileName();
        Path completePath = createCompletePath(path + logFileName);
        final boolean noteDoesNotExist = tryToCreateFile(completePath);
        if (noteDoesNotExist) {
            addHeader(completePath, action);
            addContent(content, completePath);
            printSuccessMessage();
        }

    }

    private void addContent(String content, Path completePath) {
        try {
            Files.write(
                    Paths.get(String.valueOf(completePath)),
                    content.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String generateDefaultLogFileName() {
        String logfileName = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        logfileName = logfileName.replace("T", "_").replace(":", "-").substring(0, logfileName.length() - 11);
        return logfileName;
    }


    public void addHeader(Path completePath, String action) {
        String time = createCurrentTimeString();
        String header = this.logFileName + " " + time;
        header = insertLineBreak(header);
        header += action;
        byte[] bytes = header.getBytes();
        try {
            Files.write(completePath, bytes);
            printSuccessMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSuccessMessage() {
        System.out.println("Creation of logfile was successful");
    }
}
