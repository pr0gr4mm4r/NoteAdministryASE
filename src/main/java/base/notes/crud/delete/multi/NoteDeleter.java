package base.notes.crud.delete.multi;

import base.interfaces.Deleter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static base.config.Globals.path_for_notes;

public class NoteDeleter implements Deleter {


    public NoteDeleter() throws IOException {
        deleteWholeDirectory();
    }

    private void deleteWholeDirectory() throws IOException {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path_for_notes))) {
            pathStream.map(Path::toFile).forEach(File::delete);
        }
    }
}
