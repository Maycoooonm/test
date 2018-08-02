package org.xbmc.api.type;

import com.smaato.soma.bannerutilities.constant.Values;

public enum DirectoryMask {
    Directories,
    AllFiles,
    All,
    Music,
    Video;

    public String toString() {
        switch (this) {
            case Directories:
                return "*";
            case AllFiles:
                return "*.*";
            case Music:
                return "music";
            case Video:
                return Values.FORMAT_VIDEO;
            default:
                return "/";
        }
    }
}
