package org.xbmc.api.info;

public class FileTypes {
    public static final String[] AUDIO = new String[]{"ac3", "flac", "m4a", "mp3", "mid", "ogg", "wav"};
    public static final String[] PICTURE = new String[]{"bmp", "gif", "jpeg", "jpg", "png", "tbn"};
    public static final String[] PLAYLIST = new String[]{"m3u", "pls"};
    public static final String[] VIDEO = new String[]{"avi", "flv", "mkv", "mov", "mp4", "mpg", "mpeg", "ts", "wmv", "vob"};

    private static boolean a(String[] strArr, String str) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String getExtension(String str) {
        return str.substring(str.lastIndexOf(".") + 1).toLowerCase();
    }

    public static boolean isAudio(String str) {
        return a(AUDIO, str);
    }

    public static boolean isAudioOrPlaylist(String str) {
        if (!a(AUDIO, str)) {
            if (!a(PLAYLIST, str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPicture(String str) {
        return a(PICTURE, str);
    }

    public static boolean isPlaylist(String str) {
        return a(PLAYLIST, str);
    }

    public static boolean isVideo(String str) {
        return a(VIDEO, str);
    }
}
