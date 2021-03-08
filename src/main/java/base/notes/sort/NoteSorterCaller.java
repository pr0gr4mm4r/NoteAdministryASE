package base.notes.sort;

import base.interfaces.Sorter;
import base.notes.sort.model.CriteriaMap;

import java.util.stream.Collectors;

import static base.config.Globals.scanner;

public class NoteSorterCaller {
    public NoteSorterCaller() {
        CriteriaMap criteriaMap = new CriteriaMap();
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " + criteriaMap.keySet().stream().collect(Collectors.joining(", ")) + ")");
        String criteria = scanner.nextLine();
        boolean criteriaExists = criteriaMap.containsKey(criteria);
        if(criteriaExists){
            Sorter sorter = criteriaMap.get(criteria);
            sorter.sort();
        }
    }
}
