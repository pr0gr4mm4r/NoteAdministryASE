package base.notes.sort.model.maps;

import base.interfaces.Sorter;
import base.notes.sort.sorter.RhymesNoteSorter;
import base.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;

public class CriteriaMap extends HashMap<String, Sorter> {
    public CriteriaMap(){
        this.put("Rhymes", new RhymesNoteSorter());
        this.put("WordCount", new WordCountNoteSorter());
    }
}
