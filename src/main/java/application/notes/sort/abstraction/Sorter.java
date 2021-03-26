package application.notes.sort.abstraction;

import application.notes.processors.multi.NoFilesInDirectoryException;

import java.util.List;
import java.util.Map;

public interface Sorter {
    Map<String, Integer> initialize() throws NoFilesInDirectoryException;
    List sort(Map map);
    String format(List result);
    void dialogue(String formattedResult); //abstract class
    void createLogFile(String formattedResult);
}
