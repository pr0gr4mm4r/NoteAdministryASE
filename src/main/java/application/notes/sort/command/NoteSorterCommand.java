package application.notes.sort.command;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.sort.abstraction.Sorter;
import application.notes.sort.model.maps.CriteriaMap;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.util.List;
import java.util.Map;

import static config.Globals.scanner;

public class NoteSorterCommand extends AbstractCommand {
    public NoteSorterCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException {
        final CriteriaMap criteriaMap = CriteriaMap.initializeCriteriaMap();
        final String criteriaListCommaSeparated = criteriaMap.createCriteriaListCommaSeparated();
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " +
                criteriaListCommaSeparated + ")");
        final String criteria = scanner.nextLine();
        final boolean criteriaExists = criteriaMap.containsKey(criteria);
        if (criteriaExists) {
            final Sorter noteSorter = criteriaMap.getSorterbyCriteria(criteria);
            final Map<String, Integer> map = noteSorter.initialize();
            final List result = noteSorter.sort(map);
            final String formattedResult = noteSorter.format(result);
            noteSorter.dialogue(formattedResult);
        }
    }
}
