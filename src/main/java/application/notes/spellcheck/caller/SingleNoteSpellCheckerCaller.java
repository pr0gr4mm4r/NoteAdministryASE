package application.notes.spellcheck.caller;

import application.notes.spellcheck.single.SingleNoteSpellChecker;

import static config.Globals.scanner;

public class SingleNoteSpellCheckerCaller {
    public SingleNoteSpellCheckerCaller() {
        System.out.println("Which note do you want to check spelling for?");
        String noteName = scanner.nextLine();
        new SingleNoteSpellChecker(noteName);
    }
}
