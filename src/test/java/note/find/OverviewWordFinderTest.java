package note.find;

import base.notes.find.multi.OverviewWordFinder;
import base.notes.processors.multi.MultiNoteProcessor;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class OverviewWordFinderTest {
    private OverviewWordFinder overviewWordFinder;

    @Before
    public void setup(){
        overviewWordFinder = new OverviewWordFinder("test", new MultiNoteProcessor());
    }

    @Test
    public void createNoteListTest() {
        List<Path> pathList = new ArrayList<>();
        pathList.add(Paths.get("testPath"));
        List<String> noteList = overviewWordFinder.createNoteList(pathList);
        assertTrue("NoteList not initialized successfully",noteList.size() > 0);
    }

    @Test
    public void nein() {

    }
}
