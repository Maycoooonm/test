package org.xbmc.api.object;

import org.xbmc.android.util.Crc32;

public class Actor extends Artist {
    public static final String THUMB_PREFIX = "special://profile/Thumbnails/";
    private static final long a = -7026393902334967838L;
    public String role = null;

    public Actor(int i, String str, String str2) {
        super(i, str, str2);
    }

    public Actor(int i, String str, String str2, String str3) {
        super(i, str, str2);
        this.role = str3;
    }

    public static String getThumbUri(ICoverArt iCoverArt) {
        String formatAsHexLowerCase = Crc32.formatAsHexLowerCase(iCoverArt.getCrc());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("special://profile/Thumbnails/");
        stringBuilder.append(formatAsHexLowerCase.charAt(0));
        stringBuilder.append("/");
        stringBuilder.append(formatAsHexLowerCase);
        stringBuilder.append(".tbn");
        return stringBuilder.toString();
    }

    public long getCrc() {
        if (!(this.thumbID != 0 || this.arturl == null || this.arturl.compareTo("") == 0)) {
            this.thumbID = (long) Crc32.computeLowerCase(this.arturl);
        }
        return this.thumbID;
    }

    public int getMediaType() {
        return 2;
    }

    public String getThumbUri() {
        return getThumbUri(this);
    }
}
