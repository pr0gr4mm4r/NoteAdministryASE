package application.notes.crud.delete.single;

import application.notes.crud.delete.markerinterface.Deleter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class SingleNoteDeleter implements Deleter {

    public SingleNoteDeleter() {

    }

    public void deleteSingleNote(String fileToDelete, String pathToNote) throws IOException {
        try (Stream<Path> pathStream = Files.walk(Paths.get(pathToNote))) {
            pathStream.map(Path::toFile).filter(
                    file -> file.getName().equals(fileToDelete)).forEach(File::delete);
        }
    }

    public void printSuccessMessage() {
        System.out.println("successful delete!");
    }
}
