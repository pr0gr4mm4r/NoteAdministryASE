package application.notes.wordcount.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleNoteCounterCommand extends AbstractCommand {
    public SingleNoteCounterCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to count the lines and words for?");
        String noteName = scanner.nextLine();
        new SingleNoteCounter(noteName);
    }
}
