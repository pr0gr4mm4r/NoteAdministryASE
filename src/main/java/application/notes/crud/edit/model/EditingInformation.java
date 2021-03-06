package application.notes.crud.edit.model;

import java.nio.file.Path;

public class EditingInformation {
    private final int lowerManipulationRangeCap;
    private final long upperManipulationRangeCap;
    private Path completePath;

    public int getLowerManipulationRangeCap() {
        return lowerManipulationRangeCap;
    }

    public long getUpperManipulationRangeCap() {
        return upperManipulationRangeCap;
    }

    public Path getCompletePath() {
        return completePath;
    }

    public void setCompletePath(Path completePath) {
        this.completePath = completePath;
    }

    public EditingInformation(int lowerManipulationRangeCap, long upperManipulationRangeCap, Path completePath) {
        this.lowerManipulationRangeCap = lowerManipulationRangeCap;
        this.upperManipulationRangeCap = upperManipulationRangeCap;
        this.completePath = completePath;
    }
}
