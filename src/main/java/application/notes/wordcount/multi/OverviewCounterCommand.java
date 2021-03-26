package application.notes.wordcount.multi;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.notes.wordcount.multi.OverviewCounter.initializeOverviewCounter;
import static config.Globals.path_for_notes;

public class OverviewCounterCommand extends AbstractCommand {
    public OverviewCounterCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException {
        System.out.println("In the following will be counted the words of all notes in " + path_for_notes);
        final OverviewCounter overviewCounter = initializeOverviewCounter();
        overviewCounter.printResults();
    }
}
