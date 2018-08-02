package org.xbmc.httpapi;

public class WifiStateException extends Exception {
    private static final long a = 3588074771970912287L;
    private int b;

    public WifiStateException(int i) {
        this.b = i;
    }

    public WifiStateException(String str, int i) {
        super(str);
        this.b = i;
    }

    public WifiStateException(String str, Throwable th, int i) {
        super(str, th);
        this.b = i;
    }

    public WifiStateException(Throwable th, int i) {
        super(th);
        this.b = i;
    }

    public int getState() {
        return this.b;
    }
}
