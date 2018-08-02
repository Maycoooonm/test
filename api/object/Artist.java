package org.xbmc.api.object;

import java.io.Serializable;
import org.xbmc.android.util.Crc32;

public class Artist implements Serializable, ICoverArt, INamedResource {
    public static final String THUMB_PREFIX = "special://profile/Thumbnails/";
    private static final long a = 9073064679039418773L;
    public String arturl = null;
    public String biography = null;
    public String born = null;
    public String formed = null;
    public String genres = null;
    public int id;
    public String moods = null;
    public String name;
    public String styles = null;
    public long thumbID = 0;

    public Artist(int i, String str, String str2) {
        this.id = i;
        this.name = str;
        this.arturl = str2;
    }

    public static String getFallbackThumbUri(ICoverArt iCoverArt) {
        return null;
    }

    public static String getThumbUri(ICoverArt iCoverArt) {
        return iCoverArt.getThumbUrl();
    }

    public long getCrc() {
        if (this.thumbID == 0) {
            this.thumbID = (long) Crc32.computeLowerCase(this.arturl);
        }
        return this.thumbID;
    }

    public int getFallbackCrc() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public int getMediaType() {
        return 1;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return "";
    }

    public String getShortName() {
        return this.name;
    }

    public String getThumbUri() {
        return getThumbUri(this);
    }

    public String getThumbUrl() {
        return this.arturl;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.id);
        stringBuilder.append("] ");
        stringBuilder.append(this.name);
        return stringBuilder.toString();
    }
}
