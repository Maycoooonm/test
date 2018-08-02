package org.xbmc.api.object;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xbmc.api.info.FileTypes;
import org.xbmc.httpapi.Connection;

public class FileLocation implements INamedResource {
    public String displayPath;
    public boolean isArchive = false;
    public boolean isDirectory;
    public boolean isMultipath = false;
    public int mediaType = 0;
    public String name;
    public String path;

    public FileLocation(String str) {
        if (str.contains(Connection.VALUE_SEP)) {
            boolean equals;
            String[] split = str.split(Connection.VALUE_SEP);
            this.name = split[0];
            this.path = split[1];
            if (split.length > 2) {
                equals = split[2].equals("1");
            } else {
                if (!this.path.endsWith("/")) {
                    if (!this.path.endsWith("\\")) {
                        equals = false;
                    }
                }
                equals = true;
            }
            this.isDirectory = equals;
        } else {
            String replaceAll = str.replaceAll("\\\\", "/");
            this.isDirectory = replaceAll.endsWith("/");
            this.path = str;
            if (this.isDirectory) {
                replaceAll = replaceAll.substring(0, replaceAll.lastIndexOf("/"));
            }
            this.name = replaceAll.substring(replaceAll.lastIndexOf("/") + 1);
        }
        if (this.path.endsWith(".m3u") || this.path.endsWith(".pls")) {
            this.isDirectory = false;
        }
        if (!this.path.startsWith("rar://")) {
            if (!this.path.startsWith("zip://")) {
                Matcher matcher;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                if (this.path.startsWith("shout://")) {
                    this.displayPath = "Shoutcast";
                    matcher = Pattern.compile(".*shoutcast\\.com[^\\?]+\\?genre=([^\\\\]+).*", 2).matcher(this.path);
                    if (matcher.matches()) {
                        this.name = matcher.group(1);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Shoutcast - ");
                        stringBuilder.append(this.name);
                        this.displayPath = stringBuilder.toString();
                        this.path = this.path.substring(0, this.path.length() - 1);
                    } else {
                        matcher = Pattern.compile(".*shoutcast\\.com.*tunein-station\\.pls\\?id=([0-9]+)", 2).matcher(this.path);
                        if (matcher.matches()) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Station #");
                            stringBuilder2.append(matcher.group(1));
                            this.name = stringBuilder2.toString();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Shoutcast - ");
                            stringBuilder.append(this.name);
                            this.displayPath = stringBuilder.toString();
                        }
                    }
                    a();
                }
                if (this.path.startsWith("lastfm://")) {
                    if (this.path.equals("lastfm://")) {
                        this.displayPath = this.name;
                    }
                    if (this.path.equals("lastfm://xbmc/tag/xbmc/toptags/")) {
                        this.name = "Overall Top Tags";
                        this.displayPath = this.name;
                    }
                    matcher = Pattern.compile(".*/tag/([^/]+)/", 2).matcher(this.path);
                    if (matcher.matches()) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Last.fm - Tag - ");
                        stringBuilder3.append(matcher.group(1));
                        str = stringBuilder3.toString();
                        this.displayPath = str;
                    } else {
                        matcher = Pattern.compile(".*globaltags/([^/]+)", 2).matcher(this.path);
                        if (matcher.matches()) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Listen to ");
                            stringBuilder2.append(matcher.group(1));
                            this.name = stringBuilder2.toString();
                        }
                    }
                } else if (this.path.contains("://")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.name);
                    stringBuilder.append("/");
                    this.displayPath = stringBuilder.toString();
                    this.isMultipath = true;
                } else {
                    str = this.path;
                    str = str.replaceAll("\\\\", "/");
                    this.displayPath = str;
                }
                a();
                this.isDirectory = false;
                this.mediaType = 1;
                a();
            }
        }
        if (this.isDirectory) {
            str = URLDecoder.decode(this.path.substring(0, this.path.length() - 1)).replaceAll("\\\\", "/");
            this.isArchive = true;
        } else {
            str = URLDecoder.decode(this.path).replaceAll("\\\\", "/");
            this.isArchive = false;
        }
        this.name = str.substring(str.lastIndexOf("/") + 1);
        str = URLDecoder.decode(this.path);
        str = str.replaceAll("\\\\", "/");
        this.displayPath = str;
        a();
    }

    public FileLocation(String str, String str2) {
        boolean z;
        boolean z2 = false;
        this.name = str;
        this.path = str2;
        if (!str2.endsWith("/")) {
            if (!str2.endsWith("\\")) {
                z = false;
                this.isDirectory = z;
                if ((this.isDirectory && str2.startsWith("rar://")) || str2.startsWith("zip://")) {
                    z2 = true;
                }
                this.isArchive = z2;
                this.isMultipath = str2.startsWith("multipath://");
                if (str2.contains("://")) {
                    str = str2.replaceAll("\\\\", "/");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("/");
                    str = stringBuilder.toString();
                }
                this.displayPath = str;
                a();
            }
        }
        z = true;
        this.isDirectory = z;
        z2 = true;
        this.isArchive = z2;
        this.isMultipath = str2.startsWith("multipath://");
        if (str2.contains("://")) {
            str = str2.replaceAll("\\\\", "/");
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("/");
            str = stringBuilder2.toString();
        }
        this.displayPath = str;
        a();
    }

    private void a() {
        String toLowerCase = this.path.substring(this.path.lastIndexOf(".") + 1).toLowerCase();
        if (FileTypes.isAudioOrPlaylist(toLowerCase)) {
            this.mediaType = 1;
            return;
        }
        int i;
        if (FileTypes.isVideo(toLowerCase)) {
            i = 2;
        } else if (FileTypes.isPicture(toLowerCase)) {
            i = 3;
        } else {
            return;
        }
        this.mediaType = i;
    }

    public String getShortName() {
        return this.name;
    }
}
