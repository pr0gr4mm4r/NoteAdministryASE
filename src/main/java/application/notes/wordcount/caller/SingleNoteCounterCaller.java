package application.notes.wordcount.caller;

import application.notes.wordcount.single.SingleNoteCounter;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import static config.Globals.scanner;

public class SingleNoteCounterCaller implements Caller, Interactor {

    private final String noteName;

    public SingleNoteCounterCaller() {
        System.out.println("Which note do you want to count the lines and words for?");
        noteName = scanner.nextLine();
    }

    @Override
    public void call() {
        new SingleNoteCounter(noteName);
    }

    @Override
    public void interact() {

    }
}
