package application.notes.spellcheck.multi;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.notes.spellcheck.multi.OverviewSpellChecker.initializeOverviewSpellChecker;
import static config.Globals.path_for_notes;

public class OverviewSpellCheckerCommand extends AbstractCommand {
    public OverviewSpellCheckerCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException {
        System.out.println("In the following all files of " + path_for_notes + " will be checked for correct spelling:");
        initializeOverviewSpellChecker();
    }
}
