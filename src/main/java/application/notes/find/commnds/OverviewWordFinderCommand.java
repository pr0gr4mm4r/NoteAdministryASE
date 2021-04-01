package application.notes.find.commnds;

import application.notes.find.multi.OverviewWordFinder;
import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static application.notes.processors.multi.NoteStack.*;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class OverviewWordFinderCommand extends AbstractCommand {
    public OverviewWordFinderCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException, IOException {
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        System.out.println("type in a keyword to search:");
        final String keyword = scanner.nextLine();
        final OverviewWordFinder overviewWordFinder = new OverviewWordFinder(keyword, noteStack);
        overviewWordFinder.composeOverview();
    }
}

