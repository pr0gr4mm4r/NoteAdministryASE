package base.notes.dispatch.raw;

import base.notes.dispatch.model.SendingInformation;

import java.util.Properties;

public class DispatcherRaw {
    private SendingInformation sendingInformation;

    public DispatcherRaw(SendingInformation sendingInformation) {
        this.sendingInformation = sendingInformation;
    }

    public Properties defineProperties() {
        Properties properties = System.getProperties();
        final String host = "smtp.gmail.com";
        final String port = "465";
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        return properties;
    }

    public SendingInformation getSendingInformation() {
        return sendingInformation;
    }

    public void setSendingInformation(SendingInformation sendingInformation) {
        this.sendingInformation = sendingInformation;
    }
}
