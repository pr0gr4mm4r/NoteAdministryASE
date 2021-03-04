package base.logfiles.crud.declare;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class LogFileDeclarator {
    private String logFileContent;
    public LogFileDeclarator(String logFileContent){
        this.logFileContent = logFileContent;
    }

    private String generateDefaultLogFileName() {
        String logfileName = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        logfileName = logfileName.replace("T", "_").replace(":", "-").substring(0, logfileName.length() - 11);
        return logfileName;
    }

    public String getLogFileContent() {
        return logFileContent;
    }

    public void setLogFileContent(String logFileContent) {
        this.logFileContent = logFileContent;
    }

}
