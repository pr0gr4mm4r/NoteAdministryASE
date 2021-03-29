package application.notes.crud.declare.abstraction;

import java.nio.file.Path;

public interface FileCreator {
    boolean tryToCreateFile(final Path completePath);
}
