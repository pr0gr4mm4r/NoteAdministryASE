package application;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WordExistenceMap extends HashMap<String, Boolean> { // use enum


    public WordExistenceMap(List<String> positives, List<String> negatives) {
        for (String positive : positives) {
            this.put(positive, true);
        }
        for (String negative : negatives) {
            this.put(negative, false);
        }
    }

    public List<Entry<String, Boolean>> discardNegatives(){
        List<Entry<String, Boolean>> test = this.entrySet().stream().filter(Entry::getValue).collect(Collectors.toList());
        return test;
    }
}
