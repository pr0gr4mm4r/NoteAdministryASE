package application.notes.dispatch.raw;

import java.util.Properties;

public class DispatcherRaw {

    public DispatcherRaw() {

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
}
