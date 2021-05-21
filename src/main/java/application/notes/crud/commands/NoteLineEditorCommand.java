package application.notes.crud.commands;

import application.notes.crud.edit.model.EditingInformation;
import application.notes.crud.edit.single.DisplayState;
import application.notes.crud.edit.single.NoteLineEditor;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;
import java.nio.file.Path;

import static application.notes.crud.edit.single.DisplayState.ERROR;
import static utility.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteLineEditorCommand extends AbstractCommand {
    private final NoteLineEditor noteLineEditor = new NoteLineEditor();

    public NoteLineEditorCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Which note do you want to change?");
        final String fileName = scanner.nextLine();
        final Path completePath = createCompletePath(fileName, path_for_notes);
        final DisplayState displayState = noteLineEditor.displayLineByLinesOfNote(completePath);
        if (displayState.equals(ERROR)) {
            System.out.println("error");
            return;
        }
        final long upperManipulationRangeCap = noteLineEditor.countLineLength(fileName);
        final int lowerManipulationRangeCap = 1;
        boolean editingIsPossible = noteLineEditor.editingPossible(upperManipulationRangeCap, lowerManipulationRangeCap);
        if (editingIsPossible) {
            final EditingInformation editingInformation = new EditingInformation(lowerManipulationRangeCap, upperManipulationRangeCap, completePath);
            noteLineEditor.edit(editingInformation);
        }
    }
}
