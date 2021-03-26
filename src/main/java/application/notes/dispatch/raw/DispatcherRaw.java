package application.notes.dispatch.raw;

import java.util.Properties;

public class DispatcherRaw {
    private Properties properties;

    public DispatcherRaw() {

    }

    public static DispatcherRaw initializeDispatcherRaw() {
        final DispatcherRaw dispatcherRaw = new DispatcherRaw();
        dispatcherRaw.properties = System.getProperties();
        final String host = "smtp.gmail.com";
        final String port = "465";
        dispatcherRaw.properties.setProperty("mail.smtp.port", port);
        dispatcherRaw.properties.setProperty("mail.smtp.host", host);
        dispatcherRaw.properties.setProperty("mail.smtp.auth", "true");
        dispatcherRaw.properties.put("mail.smtp.starttls.enable", "true");
        dispatcherRaw.properties.put("mail.smtp.socketFactory.port", port);
        dispatcherRaw.properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        dispatcherRaw.properties.put("mail.smtp.socketFactory.fallback", "false");
        return dispatcherRaw;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(final Properties properties) {
        this.properties = properties;
    }
}
