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

    public static SingleNoteDispatcher initializeSingleNoteDispatcher(final SendingInformation sendingInformation) {
        final SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        final DispatcherRaw dispatcherRaw = DispatcherRaw.initializeDispatcherRaw();
        final Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendingInformation.getSender(), sendingInformation.getPassword());
            }
        };
        final Session session = getDefaultInstance(dispatcherRaw.getProperties(), auth);
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

    public MimeMessage createMessage(final Session session, final SendingInformation sendingInfo) throws MessagingException {
        final Multipart multipart = createAttachment(sendingInfo);
        final MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendingInfo.getSender()));
        message.setRecipients(RecipientType.TO, parse(sendingInfo.getRecipient(), false));
        message.setSubject("Your subject line");
        message.setText("Your message");
        message.setContent(multipart);
        return message;
    }

    public Multipart createAttachment(final SendingInformation sendingInfo) throws MessagingException {
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        final DataSource source = new FileDataSource(sendingInfo.getPath());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(sendingInfo.getPath());
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
