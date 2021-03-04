package base.logfiles.crud.declare;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class LogFileDeclarator {
    private String logFileContent;
    public LogFileDeclarator(String logFileContent){
        this.logFileContent = logFileContent;
    }

    private String generateDefaultLogFileName() {
        String bla = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        bla = bla.replace("T", "_").replace(":", "-").substring(0, bla.length() - 11);
        return bla;
    }

    public String getLogFileContent() {
        return logFileContent;
    }

    public void setLogFileContent(String logFileContent) {
        this.logFileContent = logFileContent;
    }

}
