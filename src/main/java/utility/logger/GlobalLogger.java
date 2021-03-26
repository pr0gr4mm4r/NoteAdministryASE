package utility.logger;


import application.start.NoteAdministryStart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlobalLogger {
    public GlobalLogger() {
    }
    public static Logger logger(){
        return LogManager.getLogger(NoteAdministryStart.class);
    }
}
