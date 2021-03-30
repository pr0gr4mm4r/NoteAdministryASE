package application.notes.sort.command;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.sort.abstraction.NoteSorter;
import application.notes.sort.model.maps.CriteriaMap;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static config.Globals.scanner;

public class NoteSorterCommand extends AbstractCommand {
    private NoteSorter noteSorter;
    public NoteSorterCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() throws NoFilesInDirectoryException, IOException {
        final CriteriaMap criteriaMap = CriteriaMap.initializeCriteriaMap();
        final String criteriaListCommaSeparated = criteriaMap.createCriteriaListCommaSeparated();
        System.out.print("After which criteria do you want to sort notes?");
        System.out.println(" (You can choose between " +
                criteriaListCommaSeparated + ")");
        final String criteria = scanner.nextLine();
        final boolean criteriaExists = criteriaMap.containsKey(criteria);
        if (criteriaExists) {
            noteSorter = criteriaMap.getSorterbyCriteria(criteria);
            final Map map = noteSorter.initializeMapToSort();
            final List result = noteSorter.sort(map);
            final String formattedResult = noteSorter.format(result);
            noteSorter.dialogue(formattedResult);
        }
    }
}
