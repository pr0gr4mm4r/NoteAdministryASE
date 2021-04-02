package application.notes.processors.multi.exceptions;

public class NoFilesInDirectoryException extends Exception {
    public NoFilesInDirectoryException(final String path_for_notes) {
        super("Directory" + path_for_notes + "does not contain any Notes!");
    }
}
