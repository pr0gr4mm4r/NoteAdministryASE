package application.start.model;

import static application.start.NoteAdministryStart.programRun;
import static config.Globals.scanner;

public class ProgramExitCommand extends AbstractCommand {
    public ProgramExitCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        scanner.close();
        System.out.println("Exited program");
        programRun = false;
    }
}
