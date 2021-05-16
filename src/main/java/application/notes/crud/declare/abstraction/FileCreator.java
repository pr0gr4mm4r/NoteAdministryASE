package application.notes.crud.declare.abstraction;

import java.io.IOException;
import java.nio.file.Path;

public interface FileCreator { // Einführung eines Interfaces führt z.B. zu Polymorphie, da jede Klasse,
    // die das Interface in Zukunft implementiert, eine eigene Implementierung anbieten muss
    boolean tryToCreateFile(final Path completePath) throws IOException;
}
