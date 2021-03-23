package application.notes.sort.model.maps;

import application.notes.sort.abstraction.Sorter;
import application.notes.sort.sorter.RhymesNoteSorter;
import application.notes.sort.sorter.VerbCountSorter;
import application.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;

public class CriteriaMap extends HashMap<String, Sorter> {
    public CriteriaMap(){
        this.put("Rhymes", new RhymesNoteSorter());
        this.put("Verbs", new VerbCountSorter());
        this.put("WordCount", new WordCountNoteSorter());
    }
}
