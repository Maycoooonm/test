package org.xbmc.api.type;

public enum ListType {
    genres,
    artists,
    albums,
    songs,
    compilations,
    years;

    public String getSingular() {
        switch (this) {
            case genres:
                return "Genre";
            case artists:
                return "Artist";
            case albums:
                return "Album";
            case songs:
                return "Song";
            case compilations:
                return "Compilation";
            case years:
                return "Year";
            default:
                return "Unknown";
        }
    }
}
