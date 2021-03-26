package application.notes.crud.read.single;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.nio.file.Path;

import static utility.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteReaderCommand extends AbstractCommand {
    public NoteReaderCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Please provide a name for the note to read:");
        final String noteName = scanner.nextLine();
        final Path path = createCompletePath(noteName, path_for_notes);

        final NoteReader noteReader = new NoteReader();
        final String note = noteReader.readNote(path);
        noteReader.displayNote(note);
    }
}
