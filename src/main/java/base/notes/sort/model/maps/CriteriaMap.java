package base.notes.sort.model.maps;

import base.notes.sort.abstraction.Sorter;
import base.notes.sort.sorter.RhymesNoteSorter;
import base.notes.sort.sorter.VerbCountSorter;
import base.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;

public class CriteriaMap extends HashMap<String, Sorter> {
    public CriteriaMap(){
        this.put("Rhymes", new RhymesNoteSorter());
        this.put("Verbs", new VerbCountSorter());
        this.put("WordCount", new WordCountNoteSorter());
    }
}
