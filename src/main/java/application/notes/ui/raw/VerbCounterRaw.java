package application.notes.ui.raw;

import rita.RiTa;

public class VerbCounterRaw {
    private String content;
    private int counter = 0;

    public VerbCounterRaw(String content) {
        this.content = content;
    }

    public int countWords() {
        final String[] splittedContent = content.split("\\s+");

        for (String s : splittedContent) {
            final boolean isVerb = RiTa.isVerb(s);
            if (isVerb) {
                counter++;
            }
        }
        return counter;
    }
}
