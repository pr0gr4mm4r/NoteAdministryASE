package application.notes.crud.edit.model;

import java.nio.file.Path;

public class LineOverwriterInformation {
    private final Path completePath;
    private final int indexLineNumber;
    private final String replacementLine;

    public LineOverwriterInformation(final Builder builder) {
        this.completePath = builder.path;
        this.indexLineNumber = builder.indexLineNumber;
        this.replacementLine = builder.replacementline;
    }

    public static class Builder {
        private Path path;
        private int indexLineNumber;
        private String replacementline;

        public Builder path(final Path path){
            this.path = path;
            return this;
        }

        public Builder indexLineNumber(final int indexLineNumber){
            this.indexLineNumber = indexLineNumber;
            return this;
        }

        public Builder replacementLine(final String replacementLine){
            this.replacementline = replacementLine;
            return this;
        }

        public LineOverwriterInformation build(){
            return new LineOverwriterInformation(this);
        }
    }

    public Path getCompletePath() {
        return completePath;
    }

    public int getIndexLineNumber() {
        return indexLineNumber;
    }

    public String getReplacementLine() {
        return replacementLine;
    }
}
