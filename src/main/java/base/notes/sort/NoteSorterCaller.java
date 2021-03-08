package base.notes.sort;

import base.interfaces.Sorter;
import base.notes.sort.model.CriteriaMap;

import static base.config.Globals.scanner;

public class NoteSorterCaller {
    public NoteSorterCaller() {
        System.out.println("After which criteria do you want to sort notes?");
        System.out.println("You can choose between ");
        String criteria = scanner.nextLine();
        CriteriaMap criteriaMap = new CriteriaMap();
        boolean criteriaExists = criteriaMap.containsKey(criteria);
        if(criteriaExists){
            Sorter sorter = criteriaMap.get(criteria);
            sorter.sort();
        }
    }
}
