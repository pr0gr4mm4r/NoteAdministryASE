package application.notes.dispatch.single;

import application.notes.dispatch.model.SendingInformation;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import static application.notes.dispatch.single.SingleNoteDispatcher.initializeSingleNoteDispatcher;
import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteDispatcherCommand extends AbstractCommand {
    public SingleNoteDispatcherCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
        System.out.println("Provide a valid gmail address for sending:");
        final String sender = scanner.nextLine();
        System.out.println("Which password does your gmail account have?");
        final String password = scanner.nextLine();
        System.out.println("Provide the email address of the recipient");
        final String recipient = scanner.nextLine();
        System.out.println("Which file from " + path_for_notes + " do you want to attach? (Hint: filename is sufficent, no path spec needed)");
        final String fileName = scanner.nextLine();
        final String completePath = path_for_notes + fileName;
        final SendingInformation sendingInformation = new SendingInformation.Builder()
                .password(password)
                .path(completePath)
                .recipient(recipient)
                .sender(sender)
                .build();
        final SingleNoteDispatcher singleNoteDispatcher = initializeSingleNoteDispatcher(sendingInformation);
        singleNoteDispatcher.sendMessage();
    }
}
