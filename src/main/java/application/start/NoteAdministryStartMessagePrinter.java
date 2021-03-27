package application.start;


import static utility.logger.GlobalLogger.*;

public class NoteAdministryStartMessagePrinter {

    public NoteAdministryStartMessagePrinter() {
    }

    static void printStartingMessage() {
        logger().info("Enter a command of your choice" +
                " (command 'help' for help)");
    }

    static void printErrorMessage(final String commandName) {
        loggerLineBreak();
        logger().info("error -> command '" + commandName + "' does not exist!");
        loggerLineBreak();
    }
}
