package application.notes.ui.command;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static application.notes.ui.display.DisplayNotes.initializeDisplayNotes;

public class DisplayCommand extends AbstractCommand {
    public DisplayCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException, IOException {
        initializeDisplayNotes();
    }
}
