package application.start.model;

public abstract class AbstractCommand {

    private String commandName;
    private boolean active;
    private String description;

    public AbstractCommand(String commandName, String description) {
        this.commandName = commandName;
        this.active = false;
        this.description = description;
    }

    public void makeActiveDecision(String scannedCommandName) {
        if (this.commandName.equalsIgnoreCase(scannedCommandName)) {
            this.setActive(true);
        }
    }

    public abstract void execute();

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
