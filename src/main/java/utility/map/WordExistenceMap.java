package utility.map;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WordExistenceMap extends HashMap<String, Boolean> { // use enum

    public WordExistenceMap() {

    }

    public static WordExistenceMap initializeWordExistenceMap(List<String> positives, List<String> negatives){
        WordExistenceMap wordExistenceMap = new WordExistenceMap();
        for (String positive : positives) {
            wordExistenceMap.put(positive, true);
        }
        for (String negative : negatives) {
            wordExistenceMap.put(negative, false);
        }
        return wordExistenceMap;
    }

    public List<Entry<String, Boolean>> discardNegatives(){
        List<Entry<String, Boolean>> positives = this.entrySet().stream().filter(Entry::getValue).collect(Collectors.toList());
        return positives;
    }
}
