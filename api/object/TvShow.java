package org.xbmc.api.object;

import java.util.List;
import org.xbmc.android.util.Crc32;

public class TvShow implements ICoverArt, INamedResource {
    public static final String TAG = "TvShow";
    public static final String THUMB_PREFIX = "special://profile/Thumbnails/";
    private static final long a = -902152099894950269L;
    public List<Actor> actors = null;
    public String artUrl;
    public String contentRating;
    public String firstAired;
    public String genre;
    public int id;
    public String network;
    public int numEpisodes;
    public String path;
    public double rating = 0.0d;
    public List<Season> seasons = null;
    public String summary;
    public long thumbID = 0;
    public String title;
    public boolean watched;
    public int watchedEpisodes;

    public TvShow(int i, String str, String str2, double d, String str3, String str4, String str5, String str6, String str7, int i2, int i3, boolean z, String str8) {
        this.id = i;
        this.title = str;
        this.summary = str2;
        this.rating = d;
        this.firstAired = str3;
        this.contentRating = str5;
        this.network = str6;
        this.genre = str4;
        this.path = str7;
        this.numEpisodes = i2;
        this.watchedEpisodes = i3;
        this.watched = z;
        this.artUrl = str8;
    }

    public static String getFallbackThumbUri(ICoverArt iCoverArt) {
        String formatAsHexLowerCase = Crc32.formatAsHexLowerCase(iCoverArt.getMediaType() == 24 ? (long) iCoverArt.getFallbackCrc() : iCoverArt.getCrc());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("special://profile/Thumbnails/");
        stringBuilder.append(formatAsHexLowerCase.charAt(0));
        stringBuilder.append("/");
        stringBuilder.append(formatAsHexLowerCase);
        stringBuilder.append(".jpg");
        return stringBuilder.toString();
    }

    public static String getThumbUri(ICoverArt iCoverArt) {
        return iCoverArt.getThumbUrl();
    }

    public long getCrc() {
        if (this.thumbID == 0) {
            this.thumbID = (long) Crc32.computeLowerCase(this.artUrl);
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
        return 22;
    }

    public String getName() {
        return this.title;
    }

    public String getPath() {
        return this.path;
    }

    public String getShortName() {
        return this.title;
    }

    public String getThumbUri() {
        return getThumbUri(this);
    }

    public String getThumbUrl() {
        return this.artUrl;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.id);
        stringBuilder.append("] ");
        stringBuilder.append(this.title);
        return stringBuilder.toString();
    }
}
