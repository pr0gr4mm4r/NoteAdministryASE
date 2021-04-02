package unittests.notes.dispatch;

import application.notes.dispatch.model.FakeSendingInformation;
import application.notes.dispatch.model.SendingInformation;
import application.notes.dispatch.raw.DispatcherRaw;
import application.notes.dispatch.single.SingleNoteDispatcher;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import java.util.Arrays;

import static org.junit.Assert.*;


public class SingleNoteDispatcherTest {

    @Test
    public void createMessageTest() throws MessagingException {
        final DispatcherRaw dispatcherRaw = DispatcherRaw.initializeDispatcherRaw();
        final Session session = Session.getDefaultInstance(dispatcherRaw.getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });
        final SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        final SendingInformation sendingInformation = new FakeSendingInformation().getSendingInformation();

        final MimeMessage mimeMessage = singleNoteDispatcher.createMessage(session, sendingInformation);

        assertEquals("text/plain", mimeMessage.getContentType());
        assertEquals("[fakeRecipient]", Arrays.toString(mimeMessage.getAllRecipients()));
    }

    @Test
    public void createAttachmentTest() throws MessagingException {
        final SingleNoteDispatcher singleNoteDispatcher = new SingleNoteDispatcher();
        final FakeSendingInformation fakeSendingInformation = new FakeSendingInformation();
        final SendingInformation sendingInformation = fakeSendingInformation.getSendingInformation();
        
        final Multipart multipart = singleNoteDispatcher.createAttachment(sendingInformation);

        assertFalse(multipart.getContentType().isEmpty());
    }
}
