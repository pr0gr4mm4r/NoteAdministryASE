package application.start.model.specialcommands.abstractCommand;

import application.notes.processors.multi.NoFilesInDirectoryException;

public abstract class AbstractCommand {

    private String commandName;
    private boolean active;
    private String description;

    public AbstractCommand(final String commandName, final String description) {
        this.commandName = commandName;
        this.active = false;
        this.description = description;
    }

    public void makeActiveDecision(final String scannedCommandName) {
        if (this.commandName.equalsIgnoreCase(scannedCommandName)) {
            this.setActive(true);
        }
    }

    public abstract void execute() throws NoFilesInDirectoryException;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(final String commandName) {
        this.commandName = commandName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
