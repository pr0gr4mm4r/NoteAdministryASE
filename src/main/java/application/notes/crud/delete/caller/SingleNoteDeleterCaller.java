package application.notes.crud.delete.caller;

import application.notes.crud.delete.single.SingleNoteDeleter;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteDeleterCaller {
    protected String fileToDelete;
    protected String confirmation;

    public SingleNoteDeleterCaller() {
        System.out.println("Which file do you want to delete within '" + path_for_notes + "'?");
        System.out.println("Provide the name of the file including ending if present (i.e. .txt)");
        fileToDelete = scanner.nextLine();
        System.out.println("Are you sure to permanently delete this file within 'src/base/files'? ");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            new SingleNoteDeleter(fileToDelete);
        }
    }
}
