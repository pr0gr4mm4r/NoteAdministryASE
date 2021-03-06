package application.logfiles.crud.declare.single;

import application.notes.crud.declare.abstraction.FileCreator;
import application.notes.crud.declare.abstraction.HeaderAdder;
import application.notes.crud.declare.abstraction.HeaderInformation;
import utility.formatting.StringRepresentation.Header;
import utility.formatting.StringRepresentation.StringRepresentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static config.Globals.path_for_logfiles;
import static application.notes.crud.declare.single.NoteDeclarator.createCurrentTimeString;
import static utility.path.PathCreator.createCompletePath;

public class LogFileDeclarator implements HeaderAdder, FileCreator {
    private String capture;
    private String logFileName;
    private Path completePath;
    private String successMessage;

    private LogFileDeclarator() {

    }

    public static LogFileDeclarator initializeLogFileDeclarator(final String capture) {
        final LogFileDeclarator logFileDeclarator = new LogFileDeclarator();
        logFileDeclarator.capture = capture;
        logFileDeclarator.logFileName = logFileDeclarator.generateDefaultLogFileName();
        logFileDeclarator.completePath = createCompletePath(logFileDeclarator.logFileName, path_for_logfiles);
        logFileDeclarator.successMessage = "Creation of Logfile was successful";
        return logFileDeclarator;
    }

    public void declareLogFile(final String content) throws IOException {
        final boolean noteDoesNotExist = tryToCreateFile(completePath);
        if (noteDoesNotExist) {
            HeaderInformation headerInformation = new HeaderInformation(completePath, capture);
            addHeader(headerInformation);
            addContent(content, completePath);
            printSuccessMessage();
        }
    }

    private void addContent(final String content, final Path completePath) {
        try {
            Files.write(
                    Paths.get(String.valueOf(completePath)),
                    content.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String generateDefaultLogFileName() {
        String logfileName = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        logfileName = logfileName.replace("T", "_").replace(":", "-").substring(0, logfileName.length() - 11);
        return logfileName;
    }

    @Override
    public void addHeader(final HeaderInformation headerInformation) throws IOException {
        final String time = createCurrentTimeString();
        StringRepresentation header = new Header(logFileName + " " + time);
        header.insertLineBreak();
        header.add(headerInformation.getCapture());
        final byte[] bytes = header.getStringRepresentation().getBytes();
        Files.write(completePath, bytes);
    }

    public void printSuccessMessage() {
        System.out.println(successMessage);
    }
}
