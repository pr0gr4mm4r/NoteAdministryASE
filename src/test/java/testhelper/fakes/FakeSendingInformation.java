package testhelper.fakes;

import application.notes.dispatch.model.SendingInformation;

public class FakeSendingInformation {
    private final SendingInformation sendingInformation;
    public FakeSendingInformation() {
        sendingInformation = new SendingInformation.Builder()
                .path("src/main/files/notes/test")
                .sender("fakeSender")
                .recipient("fakeRecipient")
                .password("fakePassword")
                .build();
    }

    public SendingInformation getSendingInformation() {
        return sendingInformation;
    }
}
