package org.xbmc.httpapi;

public class NoSettingsException extends Exception {
    private static final long a = -5024397978225112156L;

    public NoSettingsException() {
        super("Click on \"Settings\" or use the menu in order to add an XBMC host or IP address to your configuration.");
    }
}
