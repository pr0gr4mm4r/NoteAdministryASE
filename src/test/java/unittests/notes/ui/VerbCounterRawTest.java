package unittests.notes.ui;

import application.notes.ui.raw.VerbCounterRaw;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerbCounterRawTest {

    @Test
    public void countVerbsTest() {
        String testContent = "test\nwalk run say actress";
        VerbCounterRaw verbCounterRaw = new VerbCounterRaw(testContent);
        int verbCount = verbCounterRaw.countVerbs();
        assertEquals(4, verbCount);
    }
}
