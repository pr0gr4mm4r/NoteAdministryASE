package application.singleWord;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.singleWord.SingleWordSpellchecker.initializeSingleWordSpellChecker;
import static config.Globals.scanner;

public class SingleWordSpellCheckerCommand extends AbstractCommand {
    public SingleWordSpellCheckerCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Provide an english word to check if it is correctly spelled:");
        final String wordToCheckSpelling = scanner.nextLine();
        initializeSingleWordSpellChecker(wordToCheckSpelling);
    }
}
