package application.start.model.specialcommands.exitCommand;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.start.NoteAdministryStart.programRun;
import static config.Globals.scanner;

public class ProgramExitCommand extends AbstractCommand {
    public ProgramExitCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        scanner.close();
        System.out.println("Exited program");
        programRun = false;
    }
}
