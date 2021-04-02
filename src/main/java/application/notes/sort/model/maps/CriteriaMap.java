package application.notes.sort.model.maps;

import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.sort.abstraction.NoteSorter;
import application.notes.sort.sorter.RhymesNoteSorter;
import application.notes.sort.sorter.VerbCountSorter;
import application.notes.sort.sorter.WordCountNoteSorter;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

import static application.notes.processors.multi.NoteStack.*;
import static config.Globals.path_for_notes;

public class CriteriaMap extends HashMap<String, NoteSorter> {
    private NoteStack noteStack;

    private CriteriaMap() {

    }

    public static CriteriaMap initializeCriteriaMap() throws NoFilesInDirectoryException, IOException {
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

    public NoteSorter getSorterbyCriteria(final String criteria) {
        return this.get(criteria);
    }
}
