package application.notes.crud.declare.single;

import application.start.model.AbstractCommand;

import java.nio.file.Path;

import static application.redg.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteDeclarationCommand extends AbstractCommand {
    public NoteDeclarationCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Please provide a name for the note:");
        String noteName = scanner.nextLine();
        Path pathToNote = createCompletePath(noteName, path_for_notes);

        new NoteDeclarator(pathToNote, noteName);
    }
}
