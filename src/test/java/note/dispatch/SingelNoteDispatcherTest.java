package note.dispatch;

import base.notes.dispatch.model.FakeSendingInformation;
import base.notes.dispatch.model.SendingInformation;
import base.notes.dispatch.single.SingleNoteDispatcher;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;

import static org.junit.Assert.*;


public class SingelNoteDispatcherTest {

    @Test
    public void createMessageTest() {

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
