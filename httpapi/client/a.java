package org.xbmc.httpapi.client;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.xbmc.android.util.Base64;
import org.xbmc.android.util.ImportUtilities;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.type.ThumbSize;
import org.xbmc.httpapi.Connection;

abstract class a {
    public static final String TAG = "Client-HTTPAPI";
    protected final Connection mConnection;

    a(Connection connection) {
        this.mConnection = connection;
    }

    private Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
        Options options = new Options();
        options.inSampleSize = 2;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
    }

    private Bitmap a(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i, String str, String str2) {
        int mediaType = iCoverArt.getMediaType();
        int i2 = 3;
        if (i < 3) {
            i2 = 2;
        }
        try {
            str = this.mConnection.query("FileDownload", str, iNotifiableManager);
            if (str.length() <= 0) {
                if (str2 != null) {
                    Log.i(TAG, "*** Downloaded cover has size null, retrying with fallback:");
                    str = this.mConnection.query("FileDownload", str2, iNotifiableManager);
                } else {
                    str = null;
                }
            }
            if (str != null) {
                byte[] decode = Base64.decode(str);
                if (decode.length > 0) {
                    Options a = a(iNotifiableManager, decode, i2);
                    mediaType = ImportUtilities.calculateSampleSize(a, ThumbSize.getTargetDimension(i2, mediaType, a.outWidth, a.outHeight));
                    a.inDither = true;
                    a.inSampleSize = mediaType;
                    a.inJustDecodeBounds = false;
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length, a);
                    if (mediaType == 1) {
                        decodeByteArray = a(decodeByteArray);
                    }
                    return decodeByteArray;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private android.graphics.BitmapFactory.Options a(org.xbmc.api.business.INotifiableManager r3, java.lang.String r4, int r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r2 = this;
        r5 = new android.graphics.BitmapFactory$Options;
        r5.<init>();
        r0 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x0019 }
        r1 = r2.mConnection;	 Catch:{ FileNotFoundException -> 0x0019 }
        r3 = r1.getThumbInputStreamForMicroHTTPd(r4, r3);	 Catch:{ FileNotFoundException -> 0x0019 }
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;	 Catch:{ FileNotFoundException -> 0x0019 }
        r0.<init>(r3, r4);	 Catch:{ FileNotFoundException -> 0x0019 }
        r3 = 1;	 Catch:{ FileNotFoundException -> 0x0019 }
        r5.inJustDecodeBounds = r3;	 Catch:{ FileNotFoundException -> 0x0019 }
        r3 = 0;	 Catch:{ FileNotFoundException -> 0x0019 }
        android.graphics.BitmapFactory.decodeStream(r0, r3, r5);	 Catch:{ FileNotFoundException -> 0x0019 }
    L_0x0019:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.a.a(org.xbmc.api.business.INotifiableManager, java.lang.String, int):android.graphics.BitmapFactory$Options");
    }

    private Options a(INotifiableManager iNotifiableManager, byte[] bArr, int i) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        return options;
    }

    private android.graphics.Bitmap b(org.xbmc.api.business.INotifiableManager r7, org.xbmc.api.object.ICoverArt r8, int r9, java.lang.String r10, java.lang.String r11) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        r8 = r8.getMediaType();
        r0 = 3;
        if (r9 >= r0) goto L_0x0008;
    L_0x0007:
        r0 = 2;
    L_0x0008:
        r9 = 0;
        r1 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r3 = "Starting download (";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2.append(r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2.append(r10);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r3 = ") - microhttpd";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2.append(r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = r2.toString();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r1, r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1 = r6.a(r7, r10, r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r3 = r1.outHeight;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = org.xbmc.api.type.ThumbSize.getTargetDimension(r0, r8, r2, r3);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r3 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r5 = "Pre-fetch: ";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r5 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r5 = "x";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r5 = r1.outHeight;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r5 = " => ";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4.append(r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4 = r4.toString();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r3, r4);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r3 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r4 = 1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        if (r3 >= r4) goto L_0x00c1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x005f:
        if (r11 == 0) goto L_0x00b9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x0061:
        r10 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = "Starting fallback download (";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1.append(r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1.append(r11);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = ")";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1.append(r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1 = r1.toString();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r10, r1);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1 = r6.a(r7, r11, r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = r1.outHeight;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = org.xbmc.api.type.ThumbSize.getTargetDimension(r0, r8, r10, r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r8 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = "FALLBACK-Pre-fetch: ";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = "x";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = r1.outHeight;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = " => ";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10.append(r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10 = r10.toString();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r8, r10);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r8 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        if (r8 >= r4) goto L_0x00b7;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x00b6:
        return r9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x00b7:
        r10 = r11;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        goto L_0x00c1;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x00b9:
        r8 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10 = "Fallback url is null, returning null-bitmap";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r8, r10);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        return r9;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
    L_0x00c1:
        r8 = org.xbmc.android.util.ImportUtilities.calculateSampleSize(r1, r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r11 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0.<init>();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r2 = "Sample size: ";	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0.append(r2);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0.append(r8);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        android.util.Log.i(r11, r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r11 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = r6.mConnection;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r10 = r0.getThumbInputStreamForMicroHTTPd(r10, r7);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r11.<init>(r10, r0);	 Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x013b, all -> 0x0139 }
        r1.inDither = r4;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1.inSampleSize = r8;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r10 = 0;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1.inJustDecodeBounds = r10;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r10 = android.graphics.BitmapFactory.decodeStream(r11, r9, r1);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        if (r8 != r4) goto L_0x00f9;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
    L_0x00f5:
        r10 = r6.a(r10);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
    L_0x00f9:
        r11.close();	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        if (r10 != 0) goto L_0x010b;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
    L_0x00fe:
        r8 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r10 = "Fetch: Bitmap is null!!";	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        android.util.Log.i(r8, r10);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        if (r11 == 0) goto L_0x010a;
    L_0x0107:
        r11.close();	 Catch:{ IOException -> 0x010a }
    L_0x010a:
        return r9;
    L_0x010b:
        r8 = "Client-HTTPAPI";	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0.<init>();	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1 = "Fetch: Bitmap: ";	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0.append(r1);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1 = r10.getWidth();	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0.append(r1);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1 = "x";	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0.append(r1);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r1 = r10.getHeight();	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0.append(r1);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        android.util.Log.i(r8, r0);	 Catch:{ FileNotFoundException -> 0x014a, IOException -> 0x0137 }
        if (r11 == 0) goto L_0x0136;
    L_0x0133:
        r11.close();	 Catch:{ IOException -> 0x0136 }
    L_0x0136:
        return r10;
    L_0x0137:
        r8 = move-exception;
        goto L_0x013d;
    L_0x0139:
        r7 = move-exception;
        goto L_0x0157;
    L_0x013b:
        r8 = move-exception;
        r11 = r9;
    L_0x013d:
        r7.onError(r8);	 Catch:{ all -> 0x0155 }
        r8.printStackTrace();	 Catch:{ all -> 0x0155 }
        if (r11 == 0) goto L_0x0154;
    L_0x0145:
        r11.close();	 Catch:{ IOException -> 0x0154 }
        return r9;
    L_0x0149:
        r11 = r9;
    L_0x014a:
        r7 = "Client-HTTPAPI";	 Catch:{ all -> 0x0155 }
        r8 = "Fetch: Bitmap not found";	 Catch:{ all -> 0x0155 }
        android.util.Log.i(r7, r8);	 Catch:{ all -> 0x0155 }
        if (r11 == 0) goto L_0x0154;
    L_0x0153:
        goto L_0x0145;
    L_0x0154:
        return r9;
    L_0x0155:
        r7 = move-exception;
        r9 = r11;
    L_0x0157:
        if (r9 == 0) goto L_0x015c;
    L_0x0159:
        r9.close();	 Catch:{ IOException -> 0x015c }
    L_0x015c:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.a.b(org.xbmc.api.business.INotifiableManager, org.xbmc.api.object.ICoverArt, int, java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    protected android.graphics.Bitmap getCover(org.xbmc.api.business.INotifiableManager r3, org.xbmc.api.object.ICoverArt r4, int r5, java.lang.String r6, java.lang.String r7) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r2 = this;
        r0 = org.xbmc.android.util.ClientFactory.XBMC_REV;	 Catch:{ OutOfMemoryError -> 0x0010 }
        r1 = 27770; // 0x6c7a float:3.8914E-41 double:1.372E-319;	 Catch:{ OutOfMemoryError -> 0x0010 }
        if (r0 < r1) goto L_0x000b;	 Catch:{ OutOfMemoryError -> 0x0010 }
    L_0x0006:
        r4 = r2.b(r3, r4, r5, r6, r7);	 Catch:{ OutOfMemoryError -> 0x0010 }
        return r4;	 Catch:{ OutOfMemoryError -> 0x0010 }
    L_0x000b:
        r4 = r2.a(r3, r4, r5, r6, r7);	 Catch:{ OutOfMemoryError -> 0x0010 }
        return r4;
    L_0x0010:
        r4 = new java.lang.Exception;
        r5 = "Out of memory. We're aware of this problem and we're working on it. Restarting the app should help.";
        r4.<init>(r5);
        r3.onError(r4);
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.a.getCover(org.xbmc.api.business.INotifiableManager, org.xbmc.api.object.ICoverArt, int, java.lang.String, java.lang.String):android.graphics.Bitmap");
    }
}
