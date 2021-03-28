package application.notes.sort.abstraction;

import application.notes.processors.multi.NoFilesInDirectoryException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NoteSorter {
    Map initialize() throws NoFilesInDirectoryException, IOException;
    List sort(Map map);
    String format(List result);
    void dialogue(String formattedResult) throws IOException;
    void createLogFile(String formattedResult) throws IOException;
}
