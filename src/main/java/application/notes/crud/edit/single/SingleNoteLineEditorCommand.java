package application.notes.crud.edit.single;

import application.start.model.AbstractCommand;

import java.nio.file.Path;

import static application.redg.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteLineEditorCommand extends AbstractCommand {
    public SingleNoteLineEditorCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to change?");
        String fileName = scanner.nextLine();
        Path completePath = createCompletePath(fileName, path_for_notes);
        new NoteLineEditor(completePath, fileName);
    }
}
