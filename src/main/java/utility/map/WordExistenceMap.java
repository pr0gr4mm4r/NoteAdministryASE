package utility.map;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WordExistenceMap extends HashMap<String, Boolean> {

    public WordExistenceMap() {

    }

    public static WordExistenceMap initializeWordExistenceMap(final List<String> positives, final List<String> negatives){
        final WordExistenceMap wordExistenceMap = new WordExistenceMap();
        for (final String positive : positives) {
            wordExistenceMap.put(positive, true);
        }
        for (final String negative : negatives) {
            wordExistenceMap.put(negative, false);
        }
        return wordExistenceMap;
    }
}
