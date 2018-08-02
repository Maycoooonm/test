package org.xbmc.api.object;

import java.util.ArrayList;
import org.xbmc.android.util.Crc32;

public class Episode implements ICoverArt {
    public static final String TAG = "Episode";
    private static final long a = 5317212562013683169L;
    public ArrayList<Actor> actors = null;
    public String artUrl;
    public String director;
    public int episode;
    public String fileName;
    public String firstAired;
    public int id;
    public String localPath;
    public int numWatched = -1;
    public String plot;
    public double rating = 0.0d;
    public int season;
    public String showTitle;
    public String title;
    public String writer;

    public Episode(int i, String str, String str2, double d, String str3, String str4, int i2, String str5, int i3, int i4, String str6, String str7, String str8, String str9) {
        this.id = i;
        this.title = str;
        this.plot = str2;
        this.rating = d;
        this.writer = str3;
        this.firstAired = str4;
        this.numWatched = i2;
        this.director = str5;
        this.season = i3;
        this.episode = i4;
        this.localPath = str6;
        this.showTitle = str8;
        this.fileName = str7;
        this.artUrl = str9;
    }

    public long getCrc() {
        return (long) Crc32.computeLowerCase(this.artUrl);
    }

    public int getFallbackCrc() {
        return Crc32.computeLowerCase(this.artUrl);
    }

    public int getId() {
        return this.id;
    }

    public int getMediaType() {
        return 24;
    }

    public String getName() {
        StringBuilder stringBuilder;
        String str;
        if (this.season == 0) {
            stringBuilder = new StringBuilder();
            str = "Special ";
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.season);
            str = "x";
        }
        stringBuilder.append(str);
        stringBuilder.append(this.episode);
        stringBuilder.append(": ");
        stringBuilder.append(this.title);
        return stringBuilder.toString();
    }

    public String getPath() {
        if (this.fileName.contains("://")) {
            return this.fileName;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.localPath);
        stringBuilder.append(this.fileName);
        return stringBuilder.toString();
    }

    public String getThumbUrl() {
        return this.artUrl;
    }
}
