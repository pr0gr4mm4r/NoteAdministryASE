package application.notes.dispatch.model;

public class SendingInformation {
    private final String recipient;
    private final String sender;
    private final String password;
    private final String path;


    public SendingInformation(Builder builder) {
        this.password = builder.password;
        this.path = builder.path;
        this.recipient = builder.recipient;
        this.sender = builder.sender;
    }

    public static class Builder {
        private String path;
        private String password;
        private String recipient;
        private String sender;

        public Builder password(final String password){
            this.password = password;
            return this;
        }

        public Builder path(final String path){
            this.path = path;
            return this;
        }

        public Builder recipient(final String recipient){
            this.recipient = recipient;
            return this;
        }

        public Builder sender(final String sender){
            this.sender = sender;
            return this;
        }

        public SendingInformation build(){
            return new SendingInformation(this); // throw exception eventuell wenn nicht alle params übergeben
        }
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }
}
