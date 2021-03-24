package application.notes.crud.read.single;

import application.start.model.AbstractCommand;

import java.nio.file.Path;

import static application.path.PathCreator.createCompletePath;
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
        new NoteReader(path);
    }
}
