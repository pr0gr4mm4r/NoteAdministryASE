package base.notes.crud.delete;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import static base.start.NoteAdministryStart.programIsRunning;

public class SingleNoteDeleter {
    final protected static String path = "src/main/java/base/files/";
    protected Scanner scanner;
    protected String fileToDelete;
    protected String confirmation;

    public SingleNoteDeleter() {
        this(System.in);
        if (confirmation.equals("yes")) {
            deleteSingleNote();
        }
    }

    public SingleNoteDeleter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        System.out.println("Which file do you want to delete within '" + path + "'?");
        System.out.println("Provide the name of the file including ending if present (i.e. .txt)");
        fileToDelete = scanner.nextLine();
        if (programIsRunning()) {
            System.out.println("Are you sure to permanently delete this file within 'src/base/files'? ");
            System.out.println("type 'yes' without '' to confirm or type anything else to abort:");
        }
        confirmation = scanner.nextLine();
    }

    private void deleteSingleNote() {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
            pathStream.map(Path::toFile).filter(
                    file -> file.getName().equals(fileToDelete)).forEach(File::delete);
            System.out.println("successful delete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
