package base.notes.spellcheck.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordExistenceModel {
    private Map<String, Boolean> wordExistenceOverview = new HashMap<>();

    public WordExistenceModel() {

    }
    public Map<String, Boolean> fill(List<String> positives, List<String> negatives) {
        for (String positive : positives) {
            wordExistenceOverview.put(positive, true);
        }
        for (String negative : negatives) {
            wordExistenceOverview.put(negative, false);
        }
        return wordExistenceOverview;
    }

    public Map<String, Boolean> getWordExistenceOverview() {
        return wordExistenceOverview;
    }

    public void setWordExistenceOverview(Map<String, Boolean> wordExistenceOverview) {
        this.wordExistenceOverview = wordExistenceOverview;
    }
}
