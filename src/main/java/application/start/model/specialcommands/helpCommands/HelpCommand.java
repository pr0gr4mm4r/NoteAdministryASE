package application.start.model.specialcommands.helpCommands;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

public class HelpCommand extends AbstractCommand {
    public HelpCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
    }
}
