package application.notes.spellcheck.commands;

import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import utility.formatting.StringRepresentation.Result;
import application.notes.spellcheck.multi.OverviewSpellChecker;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static application.notes.spellcheck.multi.OverviewSpellChecker.initializeOverviewSpellChecker;
import static config.Globals.path_for_notes;

public class OverviewSpellCheckerCommand extends AbstractCommand {
    public OverviewSpellCheckerCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException, IOException {
        System.out.println("In the following all files of " + path_for_notes + " will be checked for correct spelling:");
        OverviewSpellChecker overviewSpellChecker = initializeOverviewSpellChecker();
        final Result formattedResult = overviewSpellChecker.format();
        formattedResult.print();
        overviewSpellChecker.openLogFileDialogue(formattedResult);
    }
}
