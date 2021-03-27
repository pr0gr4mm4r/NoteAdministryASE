package utility.logger;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlobalLogger {
    public GlobalLogger() {
    }
    public static Logger logger(){
        return LogManager.getLogger();
    }

    public static void loggerLineBreak(){
        logger().info("");
    }
}
