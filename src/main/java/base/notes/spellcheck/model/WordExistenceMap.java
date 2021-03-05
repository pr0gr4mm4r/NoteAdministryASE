package base.notes.spellcheck.model;

import java.util.HashMap;
import java.util.List;

public class WordExistenceMap extends HashMap<String, Boolean> {

    public void fill(List<String> positives, List<String> negatives) {
        for (String positive : positives) {
            this.put(positive, true);
        }
        for (String negative : negatives) {
            this.put(negative, false);
        }
    }
}
