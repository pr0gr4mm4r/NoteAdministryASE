package application.notes.sort.caller;

import application.notes.sort.abstraction.Sorter;
import application.notes.sort.model.maps.CriteriaMap;
import application.start.model.specialCommands.abstractCommand.AbstractCommand;

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
        CriteriaMap criteriaMap = new CriteriaMap();
        String criteriaListCommaSeparated = criteriaMap.keySet().stream().collect(Collectors.joining(", "));
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " +
                criteriaListCommaSeparated + ")");
        String criteria = scanner.nextLine();
        boolean criteriaExists = criteriaMap.containsKey(criteria);
        if (criteriaExists) {
            Sorter sorter = criteriaMap.get(criteria);
            Map<String, Integer> map = sorter.initialize();
            map.entrySet().forEach(e-> System.out.println(e.getValue()));
            List result = sorter.sort(map);
            String formattedResult = sorter.format(result);
            sorter.print(formattedResult);
        }
    }
}
