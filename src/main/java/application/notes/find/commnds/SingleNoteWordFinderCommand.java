package application.notes.find.commnds;

import application.notes.find.single.SingleNoteWordFinder;
import utility.formatting.StringRepresentation.Result;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.start.NoteAdministryStart.programRun;
import static config.Globals.scanner;

public class SingleNoteWordFinderCommand extends AbstractCommand {
    public SingleNoteWordFinderCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("type in a keyword to search:");
        final String keyword = scanner.nextLine();
        System.out.println("type in the name of the note to search:");
        final String nameOfNote = scanner.nextLine();
        final SingleNoteWordFinder singleNoteWordFinder = new SingleNoteWordFinder();
        String resultString = singleNoteWordFinder.findWort(nameOfNote, keyword);
        Result result = new Result(resultString);
        if (programRun) {
            result.print();
        }
    }
}
