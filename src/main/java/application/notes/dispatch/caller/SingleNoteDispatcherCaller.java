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
        String completePath = path_for_notes + fileName;
        sendingInformation = new SendingInformation.Builder()
                .password(password)
                .path(completePath)
                .recipient(recipient)
                .sender(sender)
                .build();
        call();
    }

    @Override
    public void interact() {
        System.out.println("Provide a valid gmail address for sending:");
        sender = scanner.nextLine();
        System.out.println("Which password does your gmail account have?");
        password = scanner.nextLine();
        System.out.println("Provide the email address of the recipient");
        recipient = scanner.nextLine();
        System.out.println("Which file from " + path_for_notes + " do you want to attach? (Hint: filename is sufficent, no path spec needed)");
        fileName = scanner.nextLine();
    }

    @Override
    public void call() {
        new SingleNoteDispatcher(sendingInformation);
    }
}
