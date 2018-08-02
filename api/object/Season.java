package org.xbmc.api.object;

import java.io.Serializable;
import java.util.List;
import javax.jmdns.impl.DNSConstants;
import org.xbmc.android.util.Crc32;

public class Season implements Serializable, ICoverArt {
    private static final long a = -7652780720536304140L;
    public final String artUrl;
    public List<Episode> episodes = null;
    public final int number;
    public final TvShow show;
    public final boolean watched;

    public Season(int i, boolean z, TvShow tvShow, String str) {
        this.number = i;
        this.watched = z;
        this.show = tvShow;
        this.artUrl = str;
    }

    public long getCrc() {
        return (long) Crc32.computeLowerCase(this.artUrl);
    }

    public int getFallbackCrc() {
        return 0;
    }

    public int getId() {
        return (this.show.getId() * DNSConstants.RECORD_REAPER_INTERVAL) + this.number;
    }

    public int getMediaType() {
        return 23;
    }

    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.show.getName());
        stringBuilder.append(" ");
        stringBuilder.append(getShortName());
        return stringBuilder.toString();
    }

    public String getPath() {
        return null;
    }

    public String getShortName() {
        if (this.number <= 0) {
            return "Specials";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Season ");
        stringBuilder.append(this.number);
        return stringBuilder.toString();
    }

    public String getThumbUrl() {
        return this.artUrl;
    }

    public String toString() {
        return getName();
    }
}
