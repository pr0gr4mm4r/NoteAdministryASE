package application.notes.wordcount.multi;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import static config.Globals.path_for_notes;

public class OverviewCounterCommand extends AbstractCommand {
    public OverviewCounterCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("In the following there will be counted the words of all notes in " + path_for_notes);
        new OverviewCounter();
    }
}
