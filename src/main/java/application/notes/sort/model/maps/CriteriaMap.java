package application.notes.sort.model.maps;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.sorter.RhymesNoteSorter;
import application.notes.sort.sorter.VerbCountSorter;
import application.notes.sort.sorter.WordCountNoteSorter;

import java.util.HashMap;
import java.util.stream.Collectors;

import static application.notes.processors.multi.NoteStack.*;
import static config.Globals.path_for_notes;

public class CriteriaMap extends HashMap<String, Sorter> {
    private NoteStack noteStack;

    public CriteriaMap() {

    }

    public static CriteriaMap initializeCriteriaMap() throws NoFilesInDirectoryException {
        final CriteriaMap criteriaMap = new CriteriaMap();
        criteriaMap.noteStack = initializeNoteStack(path_for_notes);
        criteriaMap.put("Rhymes", new RhymesNoteSorter(criteriaMap.noteStack));
        criteriaMap.put("Verbs", new VerbCountSorter(criteriaMap.noteStack));
        criteriaMap.put("WordCount", new WordCountNoteSorter(criteriaMap.noteStack));
        return criteriaMap;
    }

    public String createCriteriaListCommaSeparated() {
        return this.keySet().stream().collect(Collectors.joining(", "));
    }

    public Sorter getSorterbyCriteria(final String criteria) {
        return this.get(criteria);
    }
}
