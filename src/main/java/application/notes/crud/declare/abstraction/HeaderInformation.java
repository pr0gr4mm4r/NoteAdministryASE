package application.notes.crud.declare.abstraction;

import java.nio.file.Path;

public class HeaderInformation {
    private Path path;
    private String capture;

    public HeaderInformation(Path path, String capture) {
        this.path = path;
        this.capture = capture;
    }

    public HeaderInformation(Path completePath) {
        this.path = completePath;
    }

    public Path getPath() {
        return path;
    }

    public String getCapture() {
        return capture;
    }
}
