package application.notes.ui.command;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.ui.display.DisplayNotes;
import application.notes.ui.display.DisplayTechnology;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

public class DisplayCommand extends AbstractCommand {
    public DisplayCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException, IOException {
        DisplayNotes.initializeDisplayNotes();
    }
}
