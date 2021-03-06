package base.logfiles.crud.declare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        addHeader(completePath);
    }

    private String generateDefaultLogFileName() {
        String logfileName = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        logfileName = logfileName.replace("T", "_").replace(":", "-").substring(0, logfileName.length() - 11);
        return logfileName;
    }


    public void addHeader(Path completePath) {
        String time = createCurrentTimeString();
        String header = this.logFileName + " " + time;
        header = insertLineBreak(header);
        header += action;
        byte[] bytes = header.getBytes();
        try {
            Files.write(completePath, bytes);
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
