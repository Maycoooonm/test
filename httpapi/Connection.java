package org.xbmc.httpapi;

import android.util.Log;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import org.xbmc.android.util.Base64;
import org.xbmc.android.util.ClientFactory;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.Host;

public class Connection {
    public static final String LINE_SEP = "<li>";
    public static final String PAIR_SEP = ":";
    public static final String VALUE_SEP = ";";
    private static final String a = "Connection";
    private static final String b = "/xbmcCmds/xbmcHttp";
    private static final String c = "/thumb/";
    private static final String d = "/vfs/";
    private static final int e = 5000;
    private static Connection f;
    private String g;
    private int h = 0;
    private String i = null;

    private Connection(String str, int i) {
        setHost(str, i);
    }

    private URLConnection a(URL url) {
        URLConnection openConnection = url.openConnection();
        openConnection.setConnectTimeout(5000);
        openConnection.setReadTimeout(this.h);
        openConnection.setRequestProperty(a, "close");
        if (this.i != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Basic ");
            stringBuilder.append(this.i);
            openConnection.setRequestProperty("Authorization", stringBuilder.toString());
        }
        return openConnection;
    }

    public static Connection getInstance(String str, int i) {
        if (f == null) {
            f = new Connection(str, i);
        }
        if (f.g == null) {
            f.setHost(str, i);
        }
        return f;
    }

    public static String trim(String str) {
        return new String(str.replace("</record>", "").replace("<record>", "").replace("</field>", "").toCharArray());
    }

    public static boolean trimBoolean(String str) {
        str = trim(str);
        if (str.length() <= 0 || str.startsWith("0") || str.toLowerCase().startsWith("false")) {
            return false;
        }
        if (str.startsWith("1") || str.toLowerCase().startsWith("true")) {
            return true;
        }
        return false;
    }

    public static double trimDouble(java.lang.String r5) {
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
        r5 = trim(r5);
        r0 = r5.length();
        r1 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
        if (r0 <= 0) goto L_0x0011;
    L_0x000c:
        r3 = java.lang.Double.parseDouble(r5);	 Catch:{ NumberFormatException -> 0x0011 }
        return r3;
    L_0x0011:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.trimDouble(java.lang.String):double");
    }

    public static int trimInt(java.lang.String r3) {
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
        r3 = trim(r3);
        r0 = r3.length();
        r1 = -1;
        if (r0 <= 0) goto L_0x0018;
    L_0x000b:
        r0 = ",";	 Catch:{ NumberFormatException -> 0x0018 }
        r2 = "";	 Catch:{ NumberFormatException -> 0x0018 }
        r3 = r3.replace(r0, r2);	 Catch:{ NumberFormatException -> 0x0018 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0018 }
        return r3;
    L_0x0018:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.trimInt(java.lang.String):int");
    }

    public boolean assertBoolean(INotifiableManager iNotifiableManager, String str) {
        return assertBoolean(iNotifiableManager, str, "");
    }

    public boolean assertBoolean(INotifiableManager iNotifiableManager, String str, String str2) {
        String query = query(str, str2, iNotifiableManager);
        if (!(query.contains("OK") || query.contains("true") || query.contains("True"))) {
            if (!query.contains("TRUE")) {
                if (!(query.contains("false") || query.contains("False"))) {
                    if (!query.contains("FALSE")) {
                        throw new WrongDataFormatException("OK", query);
                    }
                }
                return false;
            }
        }
        return true;
    }

    public byte[] download(java.lang.String r3) {
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
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0044 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0044 }
        r3 = r2.a(r0);	 Catch:{ Exception -> 0x0044 }
        r3 = r3.getInputStream();	 Catch:{ Exception -> 0x0044 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0044 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0044 }
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0044 }
        r1 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;	 Catch:{ Exception -> 0x0044 }
        r3.<init>(r0, r1);	 Catch:{ Exception -> 0x0044 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044 }
        r0.<init>();	 Catch:{ Exception -> 0x0044 }
    L_0x001e:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x0044 }
        if (r1 == 0) goto L_0x0028;	 Catch:{ Exception -> 0x0044 }
    L_0x0024:
        r0.append(r1);	 Catch:{ Exception -> 0x0044 }
        goto L_0x001e;	 Catch:{ Exception -> 0x0044 }
    L_0x0028:
        r3.close();	 Catch:{ Exception -> 0x0044 }
        r3 = r0.toString();	 Catch:{ Exception -> 0x0044 }
        r0 = "<html>";	 Catch:{ Exception -> 0x0044 }
        r1 = "";	 Catch:{ Exception -> 0x0044 }
        r3 = r3.replace(r0, r1);	 Catch:{ Exception -> 0x0044 }
        r0 = "</html>";	 Catch:{ Exception -> 0x0044 }
        r1 = "";	 Catch:{ Exception -> 0x0044 }
        r3 = r3.replace(r0, r1);	 Catch:{ Exception -> 0x0044 }
        r3 = org.xbmc.android.util.Base64.decode(r3);	 Catch:{ Exception -> 0x0044 }
        return r3;
    L_0x0044:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.download(java.lang.String):byte[]");
    }

    public ArrayList<String> getArray(INotifiableManager iNotifiableManager, String str, String str2) {
        String[] split = query(str, str2, iNotifiableManager).split(LINE_SEP);
        ArrayList<String> arrayList = new ArrayList();
        for (String str3 : split) {
            if (str3.length() > 0) {
                arrayList.add(str3.trim());
            }
        }
        return arrayList;
    }

    public boolean getBoolean(INotifiableManager iNotifiableManager, String str) {
        return getBoolean(iNotifiableManager, str, "");
    }

    public boolean getBoolean(org.xbmc.api.business.INotifiableManager r1, java.lang.String r2, java.lang.String r3) {
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
        r0 = this;
        r1 = r0.assertBoolean(r1, r2, r3);	 Catch:{ WrongDataFormatException -> 0x0005 }
        return r1;
    L_0x0005:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.getBoolean(org.xbmc.api.business.INotifiableManager, java.lang.String, java.lang.String):boolean");
    }

    public int getInt(INotifiableManager iNotifiableManager, String str) {
        return getInt(iNotifiableManager, str, "");
    }

    public int getInt(org.xbmc.api.business.INotifiableManager r1, java.lang.String r2, java.lang.String r3) {
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
        r0 = this;
        r1 = r0.getString(r1, r2, r3);	 Catch:{ NumberFormatException -> 0x0009 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x0009 }
        return r1;
    L_0x0009:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.getInt(org.xbmc.api.business.INotifiableManager, java.lang.String, java.lang.String):int");
    }

    public HashMap<String, String> getPairs(INotifiableManager iNotifiableManager, String str) {
        return getPairs(iNotifiableManager, str, "");
    }

    public HashMap<String, String> getPairs(INotifiableManager iNotifiableManager, String str, String str2) {
        String[] split = query(str, str2, iNotifiableManager).split(LINE_SEP);
        HashMap<String, String> hashMap = new HashMap();
        for (String split2 : split) {
            String[] split3 = split2.split(PAIR_SEP, 2);
            if (split3.length == 1) {
                hashMap.put(split3[0].trim(), "");
            } else if (split3.length == 2 && split3[0].trim().length() > 0) {
                hashMap.put(split3[0].trim(), split3[1].trim());
            }
        }
        return hashMap;
    }

    public String getString(INotifiableManager iNotifiableManager, String str) {
        return getString(iNotifiableManager, str, "");
    }

    public String getString(INotifiableManager iNotifiableManager, String str, String str2) {
        return query(str, str2, iNotifiableManager).replaceAll(LINE_SEP, "").trim();
    }

    public InputStream getThumbInputStream(String str, String str2, INotifiableManager iNotifiableManager) {
        try {
            if (this.g == null) {
                throw new NoSettingsException();
            }
            URL url = new URL(getUrl(str, str2));
            URLConnection a = a(url);
            str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Preparing input stream from ");
            stringBuilder.append(url);
            Log.i(str2, stringBuilder.toString());
            return a.getInputStream();
        } catch (Exception e) {
            iNotifiableManager.onError(e);
            return null;
        }
    }

    public InputStream getThumbInputStreamForMicroHTTPd(String str, INotifiableManager iNotifiableManager) {
        try {
            if (this.g == null) {
                throw new NoSettingsException();
            }
            StringBuilder stringBuilder;
            URL url;
            if (ClientFactory.XBMC_REV <= 0 || ClientFactory.XBMC_REV < ClientFactory.THUMB_TO_VFS_REV) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.g);
                stringBuilder.append(c);
                stringBuilder.append(str);
                stringBuilder.append(".jpg");
                url = new URL(stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.g);
                stringBuilder.append(d);
                stringBuilder.append(URLEncoder.encode(str));
                url = new URL(stringBuilder.toString());
            }
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Preparing input stream from ");
            stringBuilder.append(url);
            stringBuilder.append(" for microhttpd..");
            Log.i(str, stringBuilder.toString());
            return a(url).getInputStream();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            iNotifiableManager.onError(e2);
            return null;
        }
    }

    public String getUrl(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(this.g);
        stringBuilder.append(b);
        stringBuilder.append("?command=");
        stringBuilder.append(str);
        stringBuilder.append("(");
        stringBuilder.append(URLEncoder.encode(str2));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public java.lang.String query(java.lang.String r3, java.lang.String r4, org.xbmc.api.business.INotifiableManager r5) {
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
        r0 = 0;
        r1 = r2.g;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        if (r1 != 0) goto L_0x000b;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
    L_0x0005:
        r3 = new org.xbmc.httpapi.NoSettingsException;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        r3.<init>();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        throw r3;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
    L_0x000b:
        r1 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        r3 = r2.getUrl(r3, r4);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        r3 = r2.a(r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005e, MalformedURLException -> 0x007f }
        r4 = r1.toString();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4 = java.net.URLDecoder.decode(r4);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0 = "Connection";	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        android.util.Log.i(r0, r4);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4 = new java.io.BufferedReader;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0 = new java.io.InputStreamReader;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r1 = r3.getInputStream();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0.<init>(r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r1 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4.<init>(r0, r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0.<init>();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
    L_0x003a:
        r1 = r4.readLine();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        if (r1 == 0) goto L_0x0044;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
    L_0x0040:
        r0.append(r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        goto L_0x003a;	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
    L_0x0044:
        r4.close();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4 = r0.toString();	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0 = "<html>";	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r1 = "";	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4 = r4.replace(r0, r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r0 = "</html>";	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r1 = "";	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        r4 = r4.replace(r0, r1);	 Catch:{ MalformedURLException -> 0x007f, IOException -> 0x005c, MalformedURLException -> 0x007f }
        return r4;
    L_0x005c:
        r4 = move-exception;
        goto L_0x0061;
    L_0x005e:
        r3 = move-exception;
        r4 = r3;
        r3 = r0;
    L_0x0061:
        r0 = -1;
        if (r3 == 0) goto L_0x006c;
    L_0x0064:
        r1 = r3;	 Catch:{ IOException -> 0x006c }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ IOException -> 0x006c }
        r1 = r1.getResponseCode();	 Catch:{ IOException -> 0x006c }
        r0 = r1;
    L_0x006c:
        if (r3 == 0) goto L_0x007b;
    L_0x006e:
        r3 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r0 != r3) goto L_0x007b;
    L_0x0072:
        r4 = new org.apache.http.HttpException;
        r3 = java.lang.Integer.toString(r3);
        r4.<init>(r3);
    L_0x007b:
        r5.onError(r4);
        goto L_0x0083;
    L_0x007f:
        r3 = move-exception;
        r5.onError(r3);
    L_0x0083:
        r3 = "";
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.Connection.query(java.lang.String, java.lang.String, org.xbmc.api.business.INotifiableManager):java.lang.String");
    }

    public void setAuth(String str, String str2) {
        if (str == null || str2 == null) {
            str = null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(PAIR_SEP);
            stringBuilder.append(str2);
            str = Base64.encodeBytes(stringBuilder.toString().getBytes()).toString();
        }
        this.i = str;
    }

    public void setHost(String str, int i) {
        if (str != null) {
            if (i > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://");
                stringBuilder.append(str);
                stringBuilder.append(PAIR_SEP);
                stringBuilder.append(i);
                str = stringBuilder.toString();
                this.g = str;
            }
        }
        str = null;
        this.g = str;
    }

    public void setHost(Host host) {
        if (host == null) {
            setHost(null, 0);
            return;
        }
        setHost(host.addr, host.port);
        setAuth(host.user, host.pass);
    }

    public void setTimeout(int i) {
        if (i > 0) {
            this.h = i;
        }
    }
}
