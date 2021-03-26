package application.notes.processors.multi;

public class NoFilesInDirectoryException extends Exception {
    public NoFilesInDirectoryException(String path_for_notes) {
        super("Directory" + path_for_notes + "does not contain any Notes!");
    }
}
