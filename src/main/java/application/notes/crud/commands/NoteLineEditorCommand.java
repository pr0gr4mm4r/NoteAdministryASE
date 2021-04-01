package application.notes.crud.commands;

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
    public NoteLineEditorCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Which note do you want to change?");
        final String fileName = scanner.nextLine();
        final Path completePath = createCompletePath(fileName, path_for_notes);
        final NoteLineEditor noteLineEditor = new NoteLineEditor();
        final DisplayState displayState = noteLineEditor.displayLineByLinesOfNote(completePath);
        if (displayState.equals(ERROR)) {
            return;
        }
        final long upperManipulationRangeCap = noteLineEditor.countLineLength(fileName);
        final int lowerManipulationRangeCap = 1;
        boolean editingIsPossible = editingPossible(upperManipulationRangeCap, lowerManipulationRangeCap);
        if (editingIsPossible) {
            System.out.println("Which line do you want to overwrite? The manipulation range is: "
                    + lowerManipulationRangeCap + " - " + upperManipulationRangeCap);
            final int lineNumber = scanner.nextInt();
            scanner.nextLine();
            if (noteLineEditor.noteHasEnoughLines(completePath, lineNumber)) {
                noteLineEditor.openChangeDialogue(completePath, lineNumber);
            } else {
                noteLineEditor.openErrorDialogue(completePath);
            }
        }

    }

    private boolean editingPossible(long upperManipulationRangeCap, int lowerManipulationRangeCap) {
        return upperManipulationRangeCap > lowerManipulationRangeCap;
    }
}
