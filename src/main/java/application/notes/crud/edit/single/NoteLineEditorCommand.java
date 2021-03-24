package application.notes.crud.edit.single;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import java.nio.file.Path;

import static application.notes.crud.edit.single.DisplayState.ERROR;
import static application.path.PathCreator.createCompletePath;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class NoteLineEditorCommand extends AbstractCommand {
    public NoteLineEditorCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which note do you want to change?");
        String fileName = scanner.nextLine();
        Path completePath = createCompletePath(fileName, path_for_notes);
        NoteLineEditor noteLineEditor = new NoteLineEditor(completePath, fileName);
        DisplayState displayState = noteLineEditor.displayLineByLinesOfNote(completePath);
        if (displayState.equals(ERROR)) {
            return;
        }
        final long upperManipulationRangeCap = noteLineEditor.countLineLength(fileName);
        final int lowerManipulationRangeCap = 1;
        if (upperManipulationRangeCap > lowerManipulationRangeCap) {
            System.out.println("Which line do you want to overwrite? The manipulation range is: "
                    + lowerManipulationRangeCap + " - " + upperManipulationRangeCap);
            int lineNumber = scanner.nextInt();
            scanner.nextLine();
            if (noteLineEditor.noteHasEnoughLines(completePath, lineNumber)) {
                noteLineEditor.openChangeDialogue(completePath, lineNumber);
            } else {
                noteLineEditor.openErrorDialogue(completePath);
            }
        }
    }
}
