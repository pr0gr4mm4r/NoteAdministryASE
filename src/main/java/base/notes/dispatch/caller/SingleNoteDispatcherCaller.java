package base.notes.dispatch.caller;

import base.notes.dispatch.model.SendingInformation;
import base.notes.dispatch.single.SingleNoteDispatcher;

import static base.config.Globals.path_for_notes;
import static base.config.Globals.scanner;

public class SingleNoteDispatcherCaller {
    public SingleNoteDispatcherCaller() {
        System.out.println("Provide a valid gmail address for sending:");
        String sender = scanner.nextLine();
        System.out.println("Which password does your gmail account have?");
        String password = scanner.nextLine();
        System.out.println("Provide the email address of the recipient");
        String recipient = scanner.nextLine();
        System.out.println("Which file from " + path_for_notes + " do you want to attach? (Hint: filename is sufficent, no path spec needed)");
        String fileName = scanner.nextLine();
        String completePath = path_for_notes + fileName;
        SendingInformation sendingInformation = new SendingInformation.Builder()
                .password(password)
                .path(completePath)
                .recipient(recipient)
                .sender(sender)
                .build();
        new SingleNoteDispatcher(sendingInformation);
    }
}
