package org.xbmc.api.object;

import java.io.Serializable;
import org.xbmc.android.util.Crc32;

public class Album implements Serializable, ICoverArt, INamedResource {
    public static final String THUMB_PREFIX = "special://profile/Thumbnails/";
    private static final long a = 4779827915067184250L;
    public String artist;
    public String genres = null;
    public int id;
    public String label = null;
    public String localPath;
    public String name;
    public int rating = -1;
    public long thumbID = 0;
    public String thumbPath;
    public int year = -1;

    public Album(int i, String str, String str2, int i2) {
        this.id = i;
        this.name = str;
        this.artist = str2;
        this.year = i2;
    }

    public Album(int r3, java.lang.String r4, java.lang.String r5, int r6, java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r2 = this;
        r2.<init>();
        r0 = -1;
        r2.year = r0;
        r2.rating = r0;
        r0 = 0;
        r2.genres = r0;
        r2.label = r0;
        r0 = 0;
        r2.thumbID = r0;
        r2.id = r3;
        r2.name = r4;
        r2.artist = r5;
        r2.year = r6;
        r2.thumbPath = r7;
        r3 = "";
        r3 = r7.equals(r3);
        if (r3 != 0) goto L_0x002d;
    L_0x0023:
        r3 = org.xbmc.android.util.Crc32.computeLowerCase(r7);	 Catch:{ NumberFormatException -> 0x002b }
        r3 = (long) r3;	 Catch:{ NumberFormatException -> 0x002b }
        r2.thumbID = r3;	 Catch:{ NumberFormatException -> 0x002b }
        return;
    L_0x002b:
        r2.thumbID = r0;
    L_0x002d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.api.object.Album.<init>(int, java.lang.String, java.lang.String, int, java.lang.String):void");
    }

    public static String getFallbackThumbUri(ICoverArt iCoverArt) {
        int fallbackCrc = iCoverArt.getFallbackCrc();
        if (fallbackCrc == 0) {
            return null;
        }
        String formatAsHexLowerCase = Crc32.formatAsHexLowerCase((long) fallbackCrc);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("special://profile/Thumbnails/");
        stringBuilder.append(formatAsHexLowerCase.charAt(0));
        stringBuilder.append("/");
        stringBuilder.append(formatAsHexLowerCase);
        stringBuilder.append(".tbn");
        return stringBuilder.toString();
    }

    public static String getThumbUri(ICoverArt iCoverArt) {
        return iCoverArt.getThumbUrl();
    }

    public long getCrc() {
        return this.thumbID;
    }

    public int getFallbackCrc() {
        if (this.localPath == null) {
            return 0;
        }
        String str = this.localPath;
        return Crc32.computeLowerCase(str.substring(0, str.length() - 1));
    }

    public int getId() {
        return this.id;
    }

    public int getMediaType() {
        return 1;
    }

    public String getName() {
        return toString();
    }

    public String getPath() {
        return this.localPath;
    }

    public String getShortName() {
        return this.name;
    }

    public String getThumbUri() {
        return getThumbUri(this);
    }

    public String getThumbUrl() {
        return this.thumbPath;
    }

    public boolean isVA() {
        if (!(this.artist.equalsIgnoreCase("Various Artists") || this.artist.equalsIgnoreCase("VariousArtists") || this.artist.equalsIgnoreCase("VA") || this.artist.equalsIgnoreCase("V A") || this.artist.equalsIgnoreCase("V.A."))) {
            if (!this.artist.equalsIgnoreCase("V. A.")) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.id);
        stringBuilder.append("] ");
        stringBuilder.append(this.name);
        stringBuilder.append(" (");
        stringBuilder.append(this.artist);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
