package note.dispatch;

import base.notes.dispatch.model.FakeSendingInformation;
import base.notes.dispatch.model.SendingInformation;
import base.notes.dispatch.raw.DispatcherRaw;
import base.notes.dispatch.single.SingleNoteDispatcher;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;

import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;


public class SingleNoteDispatcherTest {

    @Test
    public void createMessageTest() throws MessagingException {
        DispatcherRaw dispatcherRaw = new DispatcherRaw();
        Properties properties = dispatcherRaw.defineProperties();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });
        SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        SendingInformation sendingInformation = new FakeSendingInformation().getSendingInformation();
        MimeMessage mimeMessage = singleNoteDispatcher.createMessage(session, sendingInformation);
        assertEquals("text/plain", mimeMessage.getContentType());
        assertEquals("[fakeRecipient]", Arrays.toString(mimeMessage.getAllRecipients()));
    }

    @Test
    public void createAttachmentTest() throws MessagingException {
        SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        FakeSendingInformation fakeSendingInformation = new FakeSendingInformation();
        SendingInformation sendingInformation = fakeSendingInformation.getSendingInformation();
        Multipart multipart = singleNoteDispatcher.createAttachment(sendingInformation);
        assertFalse(multipart.getContentType().isEmpty());
    }
}
