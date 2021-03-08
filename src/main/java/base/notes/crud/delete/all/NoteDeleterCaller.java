package base.notes.crud.delete.all;

import java.io.IOException;

import static base.config.Globals.scanner;
import static base.start.NoteAdministryStart.programIsRunning;

public class NoteDeleterCaller {
    private final String confirmation;
    public NoteDeleterCaller(){
        if (programIsRunning()) {
            System.out.println("Are you sure to permanently delete all files within 'src/base/files'? ");
            System.out.println("type 'yes' without '' to confirm or type anything else to abort:");
        }
        confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            try {
                new NoteDeleter();
                System.out.println("delete successful!");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
