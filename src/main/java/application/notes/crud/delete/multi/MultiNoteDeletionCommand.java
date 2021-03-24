package application.notes.crud.delete.multi;

import application.start.model.AbstractCommand;

public class MultiNoteDeletionCommand extends AbstractCommand {
    public MultiNoteDeletionCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {

    }
}
