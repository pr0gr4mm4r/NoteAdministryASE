package utility.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathCreator {
    private PathCreator() {
    }

    public static Path createCompletePath(final String noteName, final String path) {
        return Paths.get(path + noteName);
    }
}
