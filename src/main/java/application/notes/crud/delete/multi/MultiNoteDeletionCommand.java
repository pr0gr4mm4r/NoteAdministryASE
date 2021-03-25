package application.notes.crud.delete.multi;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static config.Globals.scanner;

public class MultiNoteDeletionCommand extends AbstractCommand {
    public MultiNoteDeletionCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Are you sure to permanently delete all files within 'src/base/files'? ");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            try {
                NoteDeleter noteDeleter = new NoteDeleter();
                noteDeleter.deleteWholeDirectory();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("delete successful!");
        }
    }
}
