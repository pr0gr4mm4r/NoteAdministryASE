package application.notes.sort.abstraction;

import application.notes.processors.multi.NoFilesInDirectoryException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NoteSorter {
    Map initializeMapToSort() throws NoFilesInDirectoryException, IOException;
    List sort(Map mapToSort);
    String format(List resultOfSorting);
    void dialogue(String formattedResultOfSorting) throws IOException;
}
