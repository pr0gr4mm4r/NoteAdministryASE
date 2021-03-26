package utility.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathCreator {
    public PathCreator() {
    }

    public static Path createCompletePath(String noteName, String path) {
        return Paths.get(path + noteName);
    }
}
