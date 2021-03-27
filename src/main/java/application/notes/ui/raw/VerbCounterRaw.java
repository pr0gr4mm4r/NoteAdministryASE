package application.notes.ui.raw;

import rita.RiTa;

public class VerbCounterRaw {
    private final String content;
    private int counter;

    public VerbCounterRaw(final String content) {
        this.content = content;
    }

    public int countVerbs() {
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
