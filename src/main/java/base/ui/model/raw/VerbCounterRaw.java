package base.ui.model.raw;

import rita.RiTa;

public class VerbCounterRaw {
    private String content;
    private int counter = 0;

    public VerbCounterRaw(String content) {
        this.content = content;
    }

    public int countWords() {
        String[] splittedContent = content.split("\\s+");

        for(int i = 0; i < splittedContent.length; i++){
            boolean isVerb = RiTa.isVerb(splittedContent[i]);
            if(isVerb){
                counter++;
            }
        }
        return counter;
    }
}
