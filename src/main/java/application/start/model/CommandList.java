package application.start.model;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandList extends ArrayList<AbstractCommand> {

    public void resetCommands() {
        this.forEach(command -> command.makeActiveDecision("e"));
    }
}
