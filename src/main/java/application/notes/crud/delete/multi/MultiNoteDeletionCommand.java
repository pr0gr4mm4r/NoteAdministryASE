package application.notes.crud.delete.multi;

import utility.confirmationString.ConfirmationString;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;

import static config.Globals.scanner;

public class MultiNoteDeletionCommand extends AbstractCommand {

    public MultiNoteDeletionCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Are you sure to permanently delete all files within 'src/base/files'? ");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        final ConfirmationString confirmationString = new ConfirmationString(scanner.nextLine());
        if (confirmationString.confirm()) {
            final NoteDeleter noteDeleter = new NoteDeleter();
            noteDeleter.deleteWholeDirectory();
            noteDeleter.printSuccessMessage();
        }
    }
}
