package application.notes.spellcheck.multi;

import application.start.model.AbstractCommand;

import static config.Globals.path_for_notes;

public class OverviewSpellCheckerCommand extends AbstractCommand {
    public OverviewSpellCheckerCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("In the following all files of" + path_for_notes + " will be checked for correct spelling:");
        new OverviewSpellChecker();
    }
}
