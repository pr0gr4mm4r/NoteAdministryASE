package application.singleWord;

import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static config.Globals.scanner;

public class SingleWordSpellCheckerCommand extends AbstractCommand {
    public SingleWordSpellCheckerCommand(final String commandName, final String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        final String wordToCheckSpelling = scanner.nextLine();
        new SingleWordSpellchecker(wordToCheckSpelling);
        System.out.println("Provide an english word to check if it is correctly spelled:");
    }
}
