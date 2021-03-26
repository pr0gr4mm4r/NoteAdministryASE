package application.notes.spellcheck.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleNoteSpellcheckerCommand  extends AbstractCommand {
    public SingleNoteSpellcheckerCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to check spelling for?");
        final String noteName = scanner.nextLine();
        SingleNoteSpellChecker.initializeSingleNoteSpellChecker(noteName);
    }
}
