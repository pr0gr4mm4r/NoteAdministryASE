package application.start.model.commands;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.util.ArrayList;

public class CommandList extends ArrayList<AbstractCommand> {

    public void resetCommands() {
        this.forEach(command -> command.makeActiveDecision("e"));
    }
}
