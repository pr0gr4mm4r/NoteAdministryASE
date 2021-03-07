package base.notes.sort.model;

import base.interfaces.Sorter;
import base.notes.sort.RhymesNoteSorter;
import base.notes.sort.WordCountNoteSorter;

import java.util.HashMap;

public class CriteriaMap extends HashMap<String, Sorter> {
    public CriteriaMap(){
        this.put("Rhymes", new RhymesNoteSorter());
        this.put("WordCount", new WordCountNoteSorter());
    }
}
