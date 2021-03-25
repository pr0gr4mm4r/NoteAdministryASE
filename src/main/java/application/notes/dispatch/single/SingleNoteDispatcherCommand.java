package application.notes.dispatch.single;

import application.notes.dispatch.model.SendingInformation;
import application.notes.dispatch.raw.DispatcherRaw;
import application.start.model.specialcommands.abstractCommand.AbstractCommand;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static config.Globals.path_for_notes;
import static config.Globals.scanner;

public class SingleNoteDispatcherCommand extends AbstractCommand {
    public SingleNoteDispatcherCommand(String commandName, String description) {
        super(commandName, description);
    }

    @Override
    public void execute() {
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
        SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        DispatcherRaw dispatcherRaw = new DispatcherRaw();
        Properties properties = dispatcherRaw.defineProperties();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendingInformation.getSender(), sendingInformation.getPassword());
            }
        };
        Session session = Session.getDefaultInstance(properties, auth);
        try {
            MimeMessage message = singleNoteDispatcher.createMessage(session, sendingInformation);
            Transport.send(message);
            singleNoteDispatcher.displaySuccessMessage();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
