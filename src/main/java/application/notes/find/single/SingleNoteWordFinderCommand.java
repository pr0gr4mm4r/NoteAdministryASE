package application.notes.find.single;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleNoteWordFinderCommand extends AbstractCommand {
    public SingleNoteWordFinderCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("type in a keyword to search:");
        String keyword = scanner.nextLine();
        System.out.println("type in the name of the note to search:");
        String nameOfNote = scanner.nextLine();
        new SingleNoteWordFinder(keyword, nameOfNote);
    }
}
