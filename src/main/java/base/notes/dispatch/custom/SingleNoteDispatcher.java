package base.notes.dispatch.custom;

import base.notes.dispatch.model.SendingInformation;
import base.notes.dispatch.raw.DispatcherRaw;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.activation.DataSource;
import javax.mail.internet.MimeMultipart;

import static base.config.Globals.scanner;

import java.util.Properties;

import static base.config.Globals.path_for_notes;

public class SingleNoteDispatcher {
    //Zum Funktionieren die Option "Zugriff durch weniger sichere Apps" im Googlekonto aktivieren

    public SingleNoteDispatcher() {
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
        DispatcherRaw dispatcherRaw = new DispatcherRaw(sendingInformation);
        Properties properties = dispatcherRaw.defineProperties();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendingInformation.getSender(), sendingInformation.getPassword());
            }
        };
        Session session = Session.getDefaultInstance(properties, auth);
        try {
            MimeMessage message = createMessage(session, sendingInformation);
            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private MimeMessage createMessage(Session session, SendingInformation sendingInfo) throws MessagingException {
        Multipart multipart = createAttachment(sendingInfo);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendingInfo.getSender()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendingInfo.getRecipient(), false));
        message.setSubject("Your subject line");
        message.setText("Your message");
        message.setContent(multipart);
        return message;
    }

    private Multipart createAttachment(SendingInformation sendingInfo) throws MessagingException {
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(sendingInfo.getPath());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(sendingInfo.getPath());
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
