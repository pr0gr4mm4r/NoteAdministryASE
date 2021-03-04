package base.notes.crud.delete;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static base.config.Globals.path;
import static base.config.Globals.scanner;
import static base.start.NoteAdministryStart.programIsRunning;

public class NoteDeleter {
    protected String confirmation;

    public NoteDeleter() {
        if (programIsRunning()) {
            System.out.println("Are you sure to permanently delete all files within 'src/base/files'? ");
            System.out.println("type 'yes' without '' to confirm or type anything else to abort:");
        }
        confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            deleteWholeDirectory();
        }
    }


    private void deleteWholeDirectory() {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
            pathStream.map(Path::toFile).forEach(File::delete);
            System.out.println("delete successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
