package org.xbmc.api.object;

import java.util.Formatter;
import javax.jmdns.impl.DNSConstants;
import org.xbmc.httpapi.Connection;

public class Song implements ICoverArt, INamedResource {
    private static final long a = 911367816075830385L;
    public String album;
    public String artist;
    public int disc;
    public int duration;
    public String filename;
    public int id;
    public String path;
    public long thumbID = 0;
    public String thumbPath;
    public String title;
    public int track;

    public Song(int r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, int r7, int r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
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
        r0 = 0;
        r2.thumbID = r0;
        r2.id = r3;
        r2.title = r4;
        r2.artist = r5;
        r2.album = r6;
        r3 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r3 = r3 & r7;
        r2.track = r3;
        r3 = r7 >> 16;
        r2.disc = r3;
        r2.duration = r8;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r9);
        r3.append(r10);
        r3 = r3.toString();
        r2.path = r3;
        r2.filename = r10;
        r2.thumbPath = r11;
        r3 = "";
        r3 = r11.equals(r3);
        if (r3 != 0) goto L_0x004e;
    L_0x0038:
        r3 = "image://";	 Catch:{ NumberFormatException -> 0x004c }
        r4 = "";	 Catch:{ NumberFormatException -> 0x004c }
        r3 = r11.replaceAll(r3, r4);	 Catch:{ NumberFormatException -> 0x004c }
        r3 = java.net.URLDecoder.decode(r3);	 Catch:{ NumberFormatException -> 0x004c }
        r3 = org.xbmc.android.util.Crc32.computeLowerCase(r3);	 Catch:{ NumberFormatException -> 0x004c }
        r3 = (long) r3;	 Catch:{ NumberFormatException -> 0x004c }
        r2.thumbID = r3;	 Catch:{ NumberFormatException -> 0x004c }
        return;
    L_0x004c:
        r2.thumbID = r0;
    L_0x004e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.api.object.Song.<init>(int, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static String getDuration(int i) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        if (i > DNSConstants.DNS_TTL) {
            stringBuilder.append(i / DNSConstants.DNS_TTL);
            stringBuilder.append(Connection.PAIR_SEP);
            i %= DNSConstants.DNS_TTL;
        }
        int i2 = i / 60;
        Formatter formatter = new Formatter();
        if (stringBuilder.length() > 0) {
            if (i2 < 10) {
                stringBuilder.append(0);
            }
            stringBuilder.append(i2);
            str = Connection.PAIR_SEP;
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(i2);
            stringBuilder2.append(Connection.PAIR_SEP);
            str = stringBuilder2.toString();
        }
        stringBuilder.append(str);
        stringBuilder.append(formatter.format("%02d", new Object[]{Integer.valueOf(i % 60)}));
        return stringBuilder.toString();
    }

    public long getCrc() {
        return this.thumbID;
    }

    public String getDuration() {
        return getDuration(this.duration);
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
        return this.title;
    }

    public String getPath() {
        return this.path;
    }

    public String getShortName() {
        return getName();
    }

    public String getThumbUrl() {
        return this.thumbPath;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.track);
        stringBuilder.append("/");
        stringBuilder.append(this.disc);
        stringBuilder.append("] ");
        stringBuilder.append(this.artist);
        stringBuilder.append(" - ");
        stringBuilder.append(this.title);
        return stringBuilder.toString();
    }
}
