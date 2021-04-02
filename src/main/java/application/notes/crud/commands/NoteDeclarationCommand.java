package application.notes.crud.commands;

import application.notes.crud.declare.single.NoteDeclarator;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static application.notes.crud.declare.single.NoteDeclarator.initializeNoteDeclarator;
import static config.Globals.scanner;

public class NoteDeclarationCommand extends AbstractCommand {
    public NoteDeclarationCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Please provide a name for the note:");
        final String noteName = scanner.nextLine();
        final NoteDeclarator noteDeclarator = initializeNoteDeclarator(noteName);
        noteDeclarator.declareNote(noteDeclarator.getPathToNote());
    }
}
