package org.xbmc.api.info;

public abstract class PlayStatus {
    public static final int PAUSED = 0;
    public static final int PLAYING = 1;
    public static final int STOPPED = 2;
    public static final int UNKNOWN = -1;

    public static int parse(String str) {
        if (!str.contains("PlayStatus:Paused")) {
            if (!str.equals("Paused")) {
                if (!str.contains("PlayStatus:Playing")) {
                    if (!str.equals("Playing")) {
                        return 2;
                    }
                }
                return 1;
            }
        }
        return 0;
    }
}
