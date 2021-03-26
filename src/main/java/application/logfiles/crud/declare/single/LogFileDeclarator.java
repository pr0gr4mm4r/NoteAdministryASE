package application.logfiles.crud.declare.single;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static config.Globals.path_for_logfiles;
import static application.notes.crud.declare.single.NoteDeclarator.createCurrentTimeString;
import static utility.formatting.BasicFormatter.insertLineBreak;

public class LogFileDeclarator {
    private String capture;
    private String logFileName;
    private Path completePath;

    private LogFileDeclarator() {

    }

    public static LogFileDeclarator initializeLogFileDeclarator(final String capture) {
        final LogFileDeclarator logFileDeclarator = new LogFileDeclarator();
        logFileDeclarator.capture = capture;
        logFileDeclarator.logFileName = logFileDeclarator.generateDefaultLogFileName();
        logFileDeclarator.completePath = createCompletePath(logFileDeclarator.logFileName);
        return logFileDeclarator;
    }

    public void declareLogFile(final String content) {
        final boolean noteDoesNotExist = this.tryToCreateFile(this.completePath);
        if (noteDoesNotExist) {
            this.addHeader(this.completePath, this.capture);
            this.addContent(content, this.completePath);
            this.printSuccessMessage();
        }
    }

    public static Path createCompletePath(final String logfileName) {
        return Paths.get(path_for_logfiles + logfileName);
    }

    public void addContent(final String content, final Path completePath) {
        try {
            Files.write(
                    Paths.get(String.valueOf(completePath)),
                    content.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean tryToCreateFile(final Path completePath) {
        try {
            Files.createFile(completePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String generateDefaultLogFileName() {
        String logfileName = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        logfileName = logfileName.replace("T", "_").replace(":", "-").substring(0, logfileName.length() - 11);
        return logfileName;
    }


    public void addHeader(final Path completePath, final String action) {
        final String time = createCurrentTimeString();
        String header = this.logFileName + " " + time;
        header = insertLineBreak(header);
        header += action;
        final byte[] bytes = header.getBytes();
        try {
            Files.write(completePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSuccessMessage() {
        System.out.println("Creation of logfile was successful");
    }
}
