package base.notes.crud.delete.single;

import base.interfaces.Deleter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static base.config.Globals.path_for_notes;


public class SingleNoteDeleter implements Deleter {

    public SingleNoteDeleter(String fileToDelete) {
        try {
            deleteSingleNote(fileToDelete);
            System.out.println("successful delete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteSingleNote(String fileToDelete) throws IOException {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path_for_notes))) {
            pathStream.map(Path::toFile).filter(
                    file -> file.getName().equals(fileToDelete)).forEach(File::delete);
        }
    }
}
