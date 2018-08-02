package org.xbmc.api.info;

public class GuiSettings {

    public static class MusicLibrary {
        public static final int LIBARY_ENABLED = 418;
        public static final int SHOW_COMPLATION_ARTISTS = 13414;
        private static final String a = "musiclibrary.";
    }

    public static class Services {
        public static final int EVENTSERVER_ENABLED = 1;
        public static final int EVENTSERVER_ENABLED_ALL = 2;
        public static final int EVENTSERVER_PORT = 3;
        public static final int EVENT_SERVER_CONTINUOUS_DELAY = 796;
        public static final int EVENT_SERVER_INITIAL_DELAY = 795;
        private static final String a = "services.";
    }

    public static String getName(int i) {
        if (i == MusicLibrary.LIBARY_ENABLED) {
            return "musiclibrary.enabled";
        }
        if (i == MusicLibrary.SHOW_COMPLATION_ARTISTS) {
            return "musiclibrary.showcompilationartists";
        }
        switch (i) {
            case 1:
                return "services.esenabled";
            case 2:
                return "services.esallinterfaces";
            case 3:
                return "services.esport";
            default:
                switch (i) {
                    case Services.EVENT_SERVER_INITIAL_DELAY /*795*/:
                        return "services.esinitialdelay";
                    case Services.EVENT_SERVER_CONTINUOUS_DELAY /*796*/:
                        return "services.escontinuousdelay";
                    default:
                        return null;
                }
        }
    }

    public static String getType(int i) {
        if (!(i == MusicLibrary.LIBARY_ENABLED || i == MusicLibrary.SHOW_COMPLATION_ARTISTS)) {
            switch (i) {
                case 1:
                case 2:
                    break;
                case 3:
                    return "3";
                default:
                    switch (i) {
                        case Services.EVENT_SERVER_INITIAL_DELAY /*795*/:
                        case Services.EVENT_SERVER_CONTINUOUS_DELAY /*796*/:
                            return "0";
                        default:
                            return null;
                    }
            }
        }
        return "1";
    }

    public static int getTypeInt(int i) {
        if (!(i == MusicLibrary.LIBARY_ENABLED || i == MusicLibrary.SHOW_COMPLATION_ARTISTS)) {
            switch (i) {
                case 1:
                case 2:
                    break;
                case 3:
                    return 3;
                default:
                    switch (i) {
                        case Services.EVENT_SERVER_INITIAL_DELAY /*795*/:
                        case Services.EVENT_SERVER_CONTINUOUS_DELAY /*796*/:
                            return 0;
                        default:
                            return -1;
                    }
            }
        }
        return 1;
    }
}
