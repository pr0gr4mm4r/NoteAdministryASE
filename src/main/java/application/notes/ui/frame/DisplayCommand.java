package application.notes.ui.frame;

import application.start.model.AbstractCommand;

public class DisplayCommand extends AbstractCommand {
    public DisplayCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        new DisplayNotes();
    }
}
