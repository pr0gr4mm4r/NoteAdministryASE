package application.notes.find.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.start.NoteAdministryStart.programRun;
import static config.Globals.scanner;

public class SingleNoteWordFinderCommand extends AbstractCommand {
    public SingleNoteWordFinderCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("type in a keyword to search:");
        final String keyword = scanner.nextLine();
        System.out.println("type in the name of the note to search:");
        final String nameOfNote = scanner.nextLine();
        final SingleNoteWordFinder singleNoteWordFinder = new SingleNoteWordFinder();
        singleNoteWordFinder.findWort(nameOfNote, keyword);
        if (programRun) {
            singleNoteWordFinder.printResults(); //NotNullValidator
        }
    }
}
