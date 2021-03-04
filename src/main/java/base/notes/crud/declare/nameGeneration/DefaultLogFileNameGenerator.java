package base.notes.crud.declare.nameGeneration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DefaultLogFileNameGenerator {
    public String generateDefaultLogFileName() {
        String bla = "Logfile_" + Instant.now().plus(1, ChronoUnit.HOURS).toString();
        bla = bla.replace("T", "_").replace(":", "-").substring(0, bla.length() - 11);
        return bla;
    }
}
