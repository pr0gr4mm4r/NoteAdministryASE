package application.notes.find.multi;

import application.notes.processors.multi.NoteStack;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static application.notes.processors.multi.NoteStack.*;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class OverviewWordFinderCommand extends AbstractCommand {
    public OverviewWordFinderCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        NoteStack noteStack = initializeNoteStack(path_for_notes);
        System.out.println("type in a keyword to search:");
        String keyword = scanner.nextLine();
        OverviewWordFinder overviewWordFinder = new OverviewWordFinder(keyword, noteStack);
        try {
            overviewWordFinder.composeOverview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
