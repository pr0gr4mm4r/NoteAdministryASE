package application.notes.spellcheck.single;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleNoteSpellcheckerCommand  extends AbstractCommand {
    public SingleNoteSpellcheckerCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to check spelling for?");
        String noteName = scanner.nextLine();
    }
}
