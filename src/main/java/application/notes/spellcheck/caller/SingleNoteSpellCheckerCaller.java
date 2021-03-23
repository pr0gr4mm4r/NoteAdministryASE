package application.notes.spellcheck.caller;

import application.notes.spellcheck.single.SingleNoteSpellChecker;
import application.start.model.Caller;
import application.start.model.Interactor;

import static config.Globals.scanner;

public class SingleNoteSpellCheckerCaller implements Caller, Interactor {
    private String noteName;

    public SingleNoteSpellCheckerCaller() {
        interact();
        call();
    }

    @Override
    public void call() {
        new SingleNoteSpellChecker(noteName);
    }

    @Override
    public void interact() {
        System.out.println("Which note do you want to check spelling for?");
        noteName = scanner.nextLine();
    }
}
