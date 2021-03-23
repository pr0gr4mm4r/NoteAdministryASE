package application.notes.crud.delete.caller;

import application.notes.crud.delete.multi.NoteDeleter;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import java.io.IOException;

import static config.Globals.scanner;

public class NoteDeleterCaller implements Caller, Interactor {
    private String confirmation;

    public NoteDeleterCaller() {
        interact();
        if (confirmation.equals("yes")) {
            call();
            System.out.println("delete successful!");
        }
    }

    @Override
    public void call() {
        try {
            new NoteDeleter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        System.out.println("Are you sure to permanently delete all files within 'src/base/files'? ");
        System.out.println("Type 'yes' without '' to confirm or type anything else to abort:");
        confirmation = scanner.nextLine();
    }
}
