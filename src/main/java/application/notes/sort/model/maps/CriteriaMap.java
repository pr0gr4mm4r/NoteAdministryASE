package application.notes.sort.model.maps;

import application.notes.processors.multi.MultiNoteProcessor;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.sorter.RhymesNoteSorter;
import application.notes.sort.sorter.VerbCountSorter;
import application.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;

import static config.Globals.path_for_notes;

public class CriteriaMap extends HashMap<String, Sorter> {

    public CriteriaMap(){
        this.put("Rhymes", new RhymesNoteSorter(new MultiNoteProcessor(path_for_notes)));
        this.put("Verbs", new VerbCountSorter(new MultiNoteProcessor(path_for_notes)));
        this.put("WordCount", new WordCountNoteSorter(new MultiNoteProcessor(path_for_notes)));
    }
}
