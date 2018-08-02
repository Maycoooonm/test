package org.xbmc.api.type;

import com.smaato.soma.bannerutilities.constant.Values;

public abstract class MediaType {
    public static final int MUSIC = 1;
    public static final int PICTURES = 3;
    public static final int UNKNOWN = -1;
    public static final int VIDEO = 2;
    public static final int VIDEO_MOVIE = 21;
    public static final int VIDEO_TVEPISODE = 24;
    public static final int VIDEO_TVSEASON = 23;
    public static final int VIDEO_TVSHOW = 22;

    public static String getArtFolder(int i) {
        switch (i) {
            case 1:
                return "/Music";
            case 2:
                break;
            case 3:
                return "/Pictures";
            default:
                switch (i) {
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        break;
                    default:
                        return "";
                }
        }
        return "/Video";
    }

    public static String getName(int i) {
        switch (i) {
            case 1:
                return "music";
            case 2:
                break;
            case 3:
                return "pictures";
            default:
                switch (i) {
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        break;
                    default:
                        return "";
                }
        }
        return Values.FORMAT_VIDEO;
    }

    public static int getPlaylistType(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                break;
            case 3:
                return 2;
            default:
                switch (i) {
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        break;
                    default:
                        return 0;
                }
        }
        return 1;
    }

    public static int[] getTypes() {
        return new int[]{1, 2, 3};
    }
}
