package application.notes.dispatch.single;

import application.notes.dispatch.model.SendingInformation;
import application.notes.dispatch.raw.DispatcherRaw;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.activation.DataSource;
import javax.mail.internet.MimeMultipart;

import static javax.mail.Session.getDefaultInstance;
import static javax.mail.internet.InternetAddress.parse;

public class SingleNoteDispatcher {
    //Zum Funktionieren die Option "Zugriff durch weniger sichere Apps" im Googlekonto aktivieren

    private MimeMessage message;

    public SingleNoteDispatcher() {

    }

    public static SingleNoteDispatcher initializeSingleNoteDispatcher(SendingInformation sendingInformation) {
        SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        DispatcherRaw dispatcherRaw = DispatcherRaw.initializeDispatcherRaw();
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendingInformation.getSender(), sendingInformation.getPassword());
            }
        };
        Session session = getDefaultInstance(dispatcherRaw.getProperties(), auth);
        try {
            singleNoteDispatcher.message = singleNoteDispatcher.createMessage(session, sendingInformation);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return singleNoteDispatcher;
    }

    void sendMessage() {
        try {
            Transport.send(message);
            displaySuccessMessage();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void displaySuccessMessage() {
        System.out.println("Sent message successfully!");
    }

    public MimeMessage createMessage(Session session, SendingInformation sendingInfo) throws MessagingException {
        Multipart multipart = createAttachment(sendingInfo);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendingInfo.getSender()));
        message.setRecipients(RecipientType.TO, parse(sendingInfo.getRecipient(), false));
        message.setSubject("Your subject line");
        message.setText("Your message");
        message.setContent(multipart);
        return message;
    }

    public Multipart createAttachment(SendingInformation sendingInfo) throws MessagingException {
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(sendingInfo.getPath());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(sendingInfo.getPath());
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
