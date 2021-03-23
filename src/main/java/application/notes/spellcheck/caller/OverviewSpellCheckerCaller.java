package application.notes.spellcheck.caller;

import application.notes.spellcheck.multi.OverviewSpellChecker;
import application.notes.interfaces.Caller;

import static config.Globals.path_for_notes;

public class OverviewSpellCheckerCaller implements Caller {
    public OverviewSpellCheckerCaller() {
        System.out.println("In the following all files of" + path_for_notes + " will be checked for correct spelling:");
    }

    @Override
    public void call() {
        new OverviewSpellChecker();
    }
}
