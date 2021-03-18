package base.notes.dispatch.model;

public class FakeSendingInformation {
    private final SendingInformation sendingInformation;
    public FakeSendingInformation() {
        sendingInformation = new SendingInformation.Builder()
                .path("base/files/notes/test")
                .sender("fakeSender")
                .recipient("fakeRecipient")
                .password("fakePassword")
                .build();
    }

    public SendingInformation getSendingInformation() {
        return sendingInformation;
    }
}
