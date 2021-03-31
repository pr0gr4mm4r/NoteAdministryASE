package application.notes.crud.declare.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;
import java.nio.file.Path;

import static utility.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteDeclarationCommand extends AbstractCommand {
    public NoteDeclarationCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Please provide a name for the note:");
        final String noteName = scanner.nextLine();
        final Path pathToNote = createCompletePath(noteName, path_for_notes);
        final NoteDeclarator noteDeclarator = new NoteDeclarator(pathToNote, noteName);
        noteDeclarator.declareNote(noteDeclarator.getPathToNote());
    }
}
