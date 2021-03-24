package application.notes.dispatch.caller;

import application.notes.dispatch.model.SendingInformation;
import application.notes.dispatch.single.SingleNoteDispatcher;
import application.notes.interfaces.Caller;
import application.notes.interfaces.Interactor;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteDispatcherCaller implements Caller, Interactor {
    private final SendingInformation sendingInformation;
    private String fileName;
    private String sender;
    private String recipient;
    private String password;

    public SingleNoteDispatcherCaller() {
        interact();

        call();
    }

    @Override
    public void interact() {

    }

    @Override
    public void call() {

    }
}
