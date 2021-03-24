package application.notes.sort.abstraction;

import java.util.List;
import java.util.Map;

public interface Sorter {
    Map<String, Integer> initialize();
    List sort(Map map);
    String format(List result);
    void print(String formattedResult);
    void logFileConfirmation(String formattedResult);
}
