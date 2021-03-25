package application.notes.sort.model.maps;

import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.sorter.RhymesNoteSorter;
import application.notes.sort.sorter.VerbCountSorter;
import application.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;

import static config.Globals.path_for_notes;

public class CriteriaMap extends HashMap<String, Sorter> {

    public CriteriaMap(){
        NoteStack noteStack = NoteStack.initializeStack(path_for_notes);
        this.put("Rhymes", new RhymesNoteSorter(noteStack));
        this.put("Verbs", new VerbCountSorter(noteStack));
        this.put("WordCount", new WordCountNoteSorter(noteStack));
    }
}
