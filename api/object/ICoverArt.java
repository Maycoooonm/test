package org.xbmc.api.object;

import java.io.Serializable;

public interface ICoverArt extends Serializable {
    long getCrc();

    int getFallbackCrc();

    int getId();

    int getMediaType();

    String getName();

    String getPath();

    String getThumbUrl();
}
