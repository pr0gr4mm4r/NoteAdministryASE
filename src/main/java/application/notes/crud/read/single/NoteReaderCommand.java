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
        String noteName = scanner.nextLine();
        Path path = createCompletePath(noteName, path_for_notes);

        NoteReader noteReader = new NoteReader();
        String note = noteReader.readNote(path);
        noteReader.displayNote(note);
    }
}
