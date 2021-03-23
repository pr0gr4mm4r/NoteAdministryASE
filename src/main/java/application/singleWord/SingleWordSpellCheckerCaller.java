package application.singleWord;

import application.start.model.Caller;
import application.start.model.Interactor;

import static config.Globals.scanner;

public class SingleWordSpellCheckerCaller implements Caller, Interactor {
    private String wordToCheckSpelling;

    public SingleWordSpellCheckerCaller() {
        interact();
        call();
    }

    @Override
    public void call() {
        System.out.println("Provide an english word to check if it is correctly spelled:");
        wordToCheckSpelling = scanner.nextLine();
    }

    @Override
    public void interact() {
        new SingleWordSpellchecker(wordToCheckSpelling);

    }
}
