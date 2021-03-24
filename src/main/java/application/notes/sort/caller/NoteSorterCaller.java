package application.notes.sort.caller;

import application.notes.sort.abstraction.Sorter;
import application.notes.sort.model.maps.CriteriaMap;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static config.Globals.scanner;

public class NoteSorterCaller implements Caller, Interactor {
    private String criteriaListCommaSeparated;
    private String criteria;
    private CriteriaMap criteriaMap;

    public NoteSorterCaller() {
        initialize();
        interact();
        boolean criteriaExists = criteriaMap.containsKey(criteria);
        if (criteriaExists) {
            call();
        }
    }

    private void initialize() {
        criteriaMap = new CriteriaMap();
        criteriaListCommaSeparated = criteriaMap.keySet().stream().collect(Collectors.joining(", "));
    }

    @Override
    public void call() {
        Sorter sorter = criteriaMap.get(criteria);
        Map<String, Integer> map = sorter.initialize();
        map.entrySet().forEach(e-> System.out.println(e.getValue()));
        List result = sorter.sort(map);
        String formattedResult = sorter.format(result);
        sorter.print(formattedResult);
    }

    @Override
    public void interact() {
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " +
                criteriaListCommaSeparated + ")");
        criteria = scanner.nextLine();
    }
}
