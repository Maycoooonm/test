package org.xbmc.httpapi.client;

import java.util.HashMap;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;

public class PictureClient {
    public static final String TAG = "PictureClient";

    static ICurrentlyPlaying a(final HashMap<String, String> hashMap) {
        return new ICurrentlyPlaying() {
            private static final long b = 5036994329211476713L;

            private int a(java.lang.String r4) {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r3 = this;
                r0 = "x";
                r4 = r4.split(r0);
                r0 = 0;
                r1 = 2;
                r2 = r4.length;
                if (r2 == r1) goto L_0x000c;
            L_0x000b:
                return r0;
            L_0x000c:
                r1 = 1;
                r4 = r4[r1];	 Catch:{ NumberFormatException -> 0x0018 }
                r4 = r4.trim();	 Catch:{ NumberFormatException -> 0x0018 }
                r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0018 }
                return r4;
            L_0x0018:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.PictureClient.1.a(java.lang.String):int");
            }

            private int b(java.lang.String r4) {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r3 = this;
                r0 = "x";
                r4 = r4.split(r0);
                r0 = 0;
                r1 = 2;
                r2 = r4.length;
                if (r2 == r1) goto L_0x000c;
            L_0x000b:
                return r0;
            L_0x000c:
                r4 = r4[r0];	 Catch:{ NumberFormatException -> 0x0017 }
                r4 = r4.trim();	 Catch:{ NumberFormatException -> 0x0017 }
                r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0017 }
                return r4;
            L_0x0017:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.PictureClient.1.b(java.lang.String):int");
            }

            public String getAlbum() {
                String[] split = ((String) hashMap.get("Filename")).replaceAll("\\\\", "/").split("/");
                return split[split.length - 2];
            }

            public String getArtist() {
                return "Image";
            }

            public int getDuration() {
                return 0;
            }

            public String getFilename() {
                return (String) hashMap.get("Filename");
            }

            public int getHeight() {
                return a((String) hashMap.get("Resolution"));
            }

            public int getMediaType() {
                return 3;
            }

            public float getPercentage() {
                return 0.0f;
            }

            public int getPlayStatus() {
                return 1;
            }

            public int getPlaylistPosition() {
                return 0;
            }

            public int getTime() {
                return 0;
            }

            public String getTitle() {
                String[] split = ((String) hashMap.get("Filename")).split("/");
                return split[split.length - 1];
            }

            public int getWidth() {
                return b((String) hashMap.get("Resolution"));
            }

            public boolean isPlaying() {
                return true;
            }
        };
    }
}
