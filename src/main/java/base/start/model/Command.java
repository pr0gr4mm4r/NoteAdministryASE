package base.start.model;

public class Command {

    private String comandName;
    private boolean active;
    private String description;

    public Command(String comandName, String description) {
        this.comandName = comandName;
        this.active = false;
        this.description = description;
    }

    public void makeActiveDecision(String scannedCommandName){
        if(this.comandName.equalsIgnoreCase(scannedCommandName)){
            this.setActive(true);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getComandName() {
        return comandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComandName(String comandName) {
        this.comandName = comandName;
    }
}
