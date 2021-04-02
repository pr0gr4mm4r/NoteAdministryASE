package application.notes.sort.abstraction;

import application.notes.processors.multi.exceptions.NoFilesInDirectoryException;
import utility.formatting.StringRepresentation.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NoteSorter {
    Map initializeMapToSort() throws NoFilesInDirectoryException, IOException;
    List sort(Map mapToSort);
    Result format(List resultOfSorting);
    void dialogue(String formattedResultOfSorting) throws IOException;
}
