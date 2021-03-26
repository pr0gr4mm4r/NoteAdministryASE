package application.notes.wordcount.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleNoteCounterCommand extends AbstractCommand {
    public SingleNoteCounterCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to count the lines and words for?");
        final String noteName = scanner.nextLine();
        SingleNoteCounter.initializeSingleNoteCounter(noteName);
    }
}
