package application.notes.ui.frame;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

public class DisplayCommand extends AbstractCommand {
    public DisplayCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException {
        new DisplayNotes();
    }
}
