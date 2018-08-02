package org.xbmc.api.object;

import java.io.Serializable;
import java.util.ArrayList;
import org.xbmc.android.util.Crc32;

public class Movie implements Serializable, ICoverArt, INamedResource {
    public static final String THUMB_PREFIX = "special://profile/Thumbnails/";
    private static final long c = 4779827915067184250L;
    private final int a;
    public ArrayList<Actor> actors = null;
    public String artUrl;
    private final String b;
    public final String director;
    public final String filename;
    public final String genres;
    public final String localPath;
    public int numVotes = -1;
    public final int numWatched;
    public String plot = null;
    public String rated = null;
    public double rating = 0.0d;
    public final String runtime;
    public String studio = null;
    public String tagline = null;
    public long thumbID = 0;
    public final String title;
    public String trailerUrl = null;
    public final int year;

    public Movie(int i, String str, int i2, String str2, String str3, String str4, String str5, String str6, double d, int i3, String str7, String str8) {
        this.a = i;
        this.title = str;
        this.year = i2;
        this.director = str4;
        this.runtime = str5;
        this.genres = str6;
        this.rating = d;
        this.localPath = str2;
        this.filename = str3;
        this.numWatched = i3;
        this.b = str7;
        this.artUrl = str8;
    }

    public static String getFallbackThumbUri(ICoverArt iCoverArt) {
        String formatAsHexLowerCase = Crc32.formatAsHexLowerCase(iCoverArt.getCrc());
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
        this.thumbID = (long) Crc32.computeLowerCase(this.artUrl);
        return this.thumbID;
    }

    public int getFallbackCrc() {
        return (this.localPath == null || this.filename == null) ? 0 : Crc32.computeLowerCase(this.artUrl);
    }

    public String getIMDbId() {
        return this.b;
    }

    public int getId() {
        return this.a;
    }

    public int getMediaType() {
        return 21;
    }

    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.title);
        stringBuilder.append(" (");
        stringBuilder.append(this.year);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String getPath() {
        if (this.filename.contains("://")) {
            return this.filename;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.localPath);
        stringBuilder.append(this.filename);
        return stringBuilder.toString();
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
        stringBuilder.append(this.a);
        stringBuilder.append("] ");
        stringBuilder.append(this.title);
        stringBuilder.append(" (");
        stringBuilder.append(this.year);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
