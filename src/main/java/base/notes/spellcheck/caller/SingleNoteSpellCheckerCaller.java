package base.notes.spellcheck.caller;

import base.notes.spellcheck.single.SingleNoteSpellChecker;

import static base.config.Globals.scanner;

public class SingleNoteSpellCheckerCaller {
    public SingleNoteSpellCheckerCaller() {
        System.out.println("Which note do you want to check spelling for?");
        String noteName = scanner.nextLine();
        new SingleNoteSpellChecker(noteName);
    }
}
