package application.start.model.specialCommands.helpCommands;

import application.start.Helper;
import application.start.model.specialCommands.abstractCommand.AbstractCommand;

public class ExtendedHelpCommand extends AbstractCommand implements Helper {
    public ExtendedHelpCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {

    }
}
