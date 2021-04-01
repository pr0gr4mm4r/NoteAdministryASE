package application.notes.spellcheck.commands;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.notes.spellcheck.single.SingleNoteSpellChecker.initializeSingleNoteSpellChecker;
import static config.Globals.scanner;

public class SingleNoteSpellcheckerCommand  extends AbstractCommand {
    public SingleNoteSpellcheckerCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to check spelling for?");
        final String noteName = scanner.nextLine();
        initializeSingleNoteSpellChecker(noteName);
    }
}
