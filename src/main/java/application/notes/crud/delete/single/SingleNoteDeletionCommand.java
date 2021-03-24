package application.notes.crud.delete.single;

import application.start.model.specialCommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteDeletionCommand extends AbstractCommand {
    public SingleNoteDeletionCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Which file do you want to delete within '" + path_for_notes + "'?");
        System.out.println("Provide the name of the file including ending if present (i.e. .txt)");
        String fileToDelete = scanner.nextLine();
        System.out.println("Are you sure to permanently delete this file within 'src/base/files'? ");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            SingleNoteDeleter singleNoteDeleter = new SingleNoteDeleter();
            try {
                singleNoteDeleter.deleteSingleNote(fileToDelete, path_for_notes);
                singleNoteDeleter.printSuccessMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
