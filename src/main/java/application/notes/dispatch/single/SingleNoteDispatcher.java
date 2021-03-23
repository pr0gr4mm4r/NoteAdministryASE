package application.notes.dispatch.single;

import application.notes.dispatch.model.SendingInformation;
import application.notes.dispatch.raw.DispatcherRaw;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.activation.DataSource;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

public class SingleNoteDispatcher {
    //Zum Funktionieren die Option "Zugriff durch weniger sichere Apps" im Googlekonto aktivieren

    public SingleNoteDispatcher(SendingInformation sendingInformation) {
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
            MimeMessage message = createMessage(session, sendingInformation);
            Transport.send(message);
            displaySuccessMessage();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public SingleNoteDispatcher() {
    }

    public void displaySuccessMessage() {
        System.out.println("Sent message successfully!");
    }

    public MimeMessage createMessage(Session session, SendingInformation sendingInfo) throws MessagingException {
        Multipart multipart = createAttachment(sendingInfo);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendingInfo.getSender()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendingInfo.getRecipient(), false));
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
