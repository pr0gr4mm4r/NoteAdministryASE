package application.notes.sort.command;

import application.notes.sort.abstraction.Sorter;
import application.notes.sort.model.maps.CriteriaMap;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static config.Globals.scanner;

public class NoteSorterCommand extends AbstractCommand {
    public NoteSorterCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        CriteriaMap criteriaMap = CriteriaMap.initializeCriteriaMap();
        String criteriaListCommaSeparated = criteriaMap.createCriteriaListCommaSeparated();
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " +
                criteriaListCommaSeparated + ")");
        String criteria = scanner.nextLine();
        boolean criteriaExists = criteriaMap.containsKey(criteria);
        if (criteriaExists) {
            Sorter sorter = criteriaMap.getSorterbyCriteria(criteria);
            Map<String, Integer> map = sorter.initialize();
            List result = sorter.sort(map);
            String formattedResult = sorter.format(result);
            sorter.dialogue(formattedResult);
        }
    }
}
