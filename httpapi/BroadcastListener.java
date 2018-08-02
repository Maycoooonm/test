package org.xbmc.httpapi;

import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.xbmc.android.remote_ali.business.Command;
import org.xbmc.api.business.DataResponse;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;

public class BroadcastListener extends Observable implements Runnable {
    public static final int EVENT_AVAILABLE = 100;
    public static final int EVENT_ERROR = -1;
    public static final int EVENT_ON_ACTION = 3;
    public static final int EVENT_ON_MEDIA_CHANGED = 12;
    public static final int EVENT_ON_PLAYBACK_ENDED = 5;
    public static final int EVENT_ON_PLAYBACK_PAUSED = 7;
    public static final int EVENT_ON_PLAYBACK_RESUMED = 8;
    public static final int EVENT_ON_PLAYBACK_SEEK = 9;
    public static final int EVENT_ON_PLAYBACK_SPEEDCHANGE = 10;
    public static final int EVENT_ON_PLAYBACK_STARTED = 4;
    public static final int EVENT_ON_PLAYBACK_STOPPED = 6;
    public static final int EVENT_ON_PROGRESS_CHANGED = 13;
    public static final int EVENT_ON_QUEUE_NEXT_ITEM = 11;
    public static final int EVENT_SHUTDOWN = 2;
    public static final int EVENT_STARTUP = 1;
    public static final int EVENT_TIMEOUT = 101;
    public static final int EVENT_UNKNOWN = 0;
    private static final String a = "broadcast";
    private static final String b = "BroadcastListener";
    private static final String c = "BroadcastTimer";
    private static final String d = "OnXbmcRemoteTest";
    private static final int e = 10;
    private static final int f = 8278;
    private static final int g = 256;
    private static final int h = 2;
    private static final String i = "255.255.255.255";
    private static final Timer j = new Timer(c);
    private static BroadcastListener k;
    private static Thread l;
    private final HttpApi m;
    private boolean n = false;
    private boolean o = false;
    private int p = 0;
    private final INotifiableManager q;

    public static class Event {
        public final int id;
        public final String[] params;

        public Event(int i, String[] strArr) {
            this.id = i;
            this.params = strArr;
        }

        public int getInt(int r3) {
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
            r2 = this;
            r0 = r2.params;	 Catch:{ NumberFormatException -> 0x0013 }
            r0 = r0.length;	 Catch:{ NumberFormatException -> 0x0013 }
            if (r0 <= 0) goto L_0x0013;	 Catch:{ NumberFormatException -> 0x0013 }
        L_0x0005:
            r0 = r2.params;	 Catch:{ NumberFormatException -> 0x0013 }
            r1 = 0;	 Catch:{ NumberFormatException -> 0x0013 }
            r0 = r0[r1];	 Catch:{ NumberFormatException -> 0x0013 }
            r0 = java.lang.Integer.valueOf(r0);	 Catch:{ NumberFormatException -> 0x0013 }
            r0 = r0.intValue();	 Catch:{ NumberFormatException -> 0x0013 }
            r3 = r0;
        L_0x0013:
            return r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.BroadcastListener.Event.getInt(int):int");
        }
    }

    private class a extends TimerTask {
        final /* synthetic */ BroadcastListener a;
        private int b;
        private int c;

        a(BroadcastListener broadcastListener, int i, int i2) {
            this.a = broadcastListener;
            this.b = i;
            this.c = i2;
        }

        public void run() {
            String[] strArr = new String[1];
            int i = this.b;
            this.b = i + 1;
            strArr[0] = String.valueOf(i);
            this.a.a(13, strArr);
            if (this.b > this.c) {
                cancel();
            }
        }
    }

    private BroadcastListener(HttpApi httpApi) {
        this.m = httpApi;
        b();
        this.q = new INotifiableManager(this) {
            final /* synthetic */ BroadcastListener a;

            {
                this.a = r1;
            }

            public void onError(Exception exception) {
            }

            public void onFinish(DataResponse<?> dataResponse) {
            }

            public void onMessage(int i, String str) {
            }

            public void onMessage(String str) {
            }

            public void onWrongConnectionState(int i, Command<?> command) {
            }

            public void retryAll() {
            }
        };
    }

    private void a(int i) {
        a(i, new String[0]);
    }

    private void a(int i, String[] strArr) {
        setChanged();
        notifyObservers(new Event(i, strArr));
    }

    private void a(String str) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RECEIVED: ");
        stringBuilder.append(str);
        Log.i(str2, stringBuilder.toString());
        str2 = "";
        int i = 1;
        if (str.contains(Connection.PAIR_SEP)) {
            str2 = str.substring(str.indexOf(Connection.PAIR_SEP) + 1, str.lastIndexOf(Connection.VALUE_SEP));
        }
        String[] strArr = null;
        if (!str.startsWith("StartUp")) {
            if (str.startsWith("ShutDown")) {
                i = 2;
            } else {
                int i2;
                if (str.startsWith("OnAction")) {
                    i2 = 3;
                    strArr = new String[]{str2};
                } else {
                    Timer timer;
                    TimerTask aVar;
                    ICurrentlyPlaying currentlyPlaying;
                    if (str.startsWith("OnPlayBackStarted")) {
                        i = 4;
                        currentlyPlaying = this.m.control.getCurrentlyPlaying(this.q);
                        timer = j;
                        aVar = new a(this, currentlyPlaying.getTime(), currentlyPlaying.getDuration());
                    } else {
                        if (str.startsWith("OnPlayBackStopped")) {
                            i = 6;
                        } else if (str.startsWith("OnPlayBackEnded")) {
                            i = 5;
                        } else if (str.startsWith("OnPlayBackPaused")) {
                            i = 7;
                        } else if (str.startsWith("OnPlayBackResumed")) {
                            i = 8;
                            currentlyPlaying = this.m.control.getCurrentlyPlaying(this.q);
                            timer = j;
                            aVar = new a(this, currentlyPlaying.getTime(), currentlyPlaying.getDuration());
                        } else if (str.startsWith("OnQueueNextItem")) {
                            i = 11;
                        } else if (str.startsWith("MediaChanged")) {
                            i = 12;
                            strArr = str2.split(Connection.LINE_SEP);
                        } else if (str.startsWith(d)) {
                            i2 = 100;
                            this.o = true;
                        } else {
                            i = 0;
                        }
                        j.cancel();
                    }
                    timer.schedule(aVar, 0, 1000);
                }
                i = i2;
            }
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        a(i, strArr);
    }

    private synchronized void b() {
        new Thread(this, "BroadcastListener-INIT") {
            final /* synthetic */ BroadcastListener a;

            public void run() {
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
                r5 = this;
                r0 = "broadcast";
                r1 = "init start...";
                android.util.Log.i(r0, r1);
                r0 = r5.a;
                r0 = r0.m;
                r0 = r0.control;
                r1 = r5.a;
                r1 = r1.q;
                r0 = r0.getBroadcast(r1);
                r1 = "broadcast";
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "current port = ";
                r2.append(r3);
                r2.append(r0);
                r2 = r2.toString();
                android.util.Log.i(r1, r2);
                r1 = 0;
                if (r0 == 0) goto L_0x0053;
            L_0x0032:
                r2 = 8278; // 0x2056 float:1.16E-41 double:4.09E-320;
                if (r0 != r2) goto L_0x0037;
            L_0x0036:
                goto L_0x0053;
            L_0x0037:
                r2 = r5.a;
                r2.p = r0;
                r2 = "broadcast";
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "keeping port ";
                r3.append(r4);
                r3.append(r0);
                r0 = r3.toString();
                android.util.Log.i(r2, r0);
                goto L_0x00a8;
            L_0x0053:
                r0 = new java.util.Random;
                r0.<init>();
                r0 = r0.nextInt();
                r0 = r0 % 22768;
                r0 = r0 + 10000;
                r2 = "broadcast";
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "new port = ";
                r3.append(r4);
                r3.append(r0);
                r3 = r3.toString();
                android.util.Log.i(r2, r3);
                r2 = r5.a;
                r2 = r2.m;
                r2 = r2.control;
                r3 = r5.a;
                r3 = r3.q;
                r4 = 2;
                r2 = r2.setBroadcast(r3, r0, r4);
                if (r2 != 0) goto L_0x00a3;
            L_0x008b:
                r0 = "broadcast";
                r2 = "SETTING BROADCAST SETTINGS FAILED!";
                android.util.Log.i(r0, r2);
                r0 = r5.a;
                r2 = -1;
                r0.a(r2);
                r0 = r5.a;
                r0.o = r1;
                r0 = r5.a;
                r0.p = r1;
                return;
            L_0x00a3:
                r2 = r5.a;
                r2.p = r0;
            L_0x00a8:
                r0 = org.xbmc.httpapi.BroadcastListener.l;
                r0.start();
            L_0x00af:
                r0 = r5.a;
                r0 = r0.o;
                if (r0 == 0) goto L_0x00bf;
            L_0x00b7:
                r0 = r5.a;
                r0 = r0.n;
                if (r0 != 0) goto L_0x00f5;
            L_0x00bf:
                r0 = "broadcast";
                r2 = "broadcast PING (OnXbmcRemoteTest)...";
                android.util.Log.i(r0, r2);
                r0 = r5.a;
                r0 = r0.m;
                r0 = r0.control;
                r2 = r5.a;
                r2 = r2.q;
                r3 = "OnXbmcRemoteTest";
                r0.broadcast(r2, r3);
                r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x00f5 }
                r0 = r1 + 1;
                r2 = 10;
                if (r1 <= r2) goto L_0x00f3;
            L_0x00e4:
                r0 = "broadcast";
                r1 = "TIMEOUT waiting for ping packet.";
                android.util.Log.i(r0, r1);
                r0 = r5.a;
                r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
                r0.a(r1);
                goto L_0x00f5;
            L_0x00f3:
                r1 = r0;
                goto L_0x00af;
            L_0x00f5:
                r0 = "broadcast";
                r1 = "EXITED firstrun loop!";
                android.util.Log.i(r0, r1);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.BroadcastListener.2.run():void");
            }
        }.start();
    }

    public static BroadcastListener getInstance(HttpApi httpApi) {
        if (k == null) {
            Log.i(a, "creating instance..");
            k = new BroadcastListener(httpApi);
            l = new Thread(k, b);
        }
        return k;
    }

    public void run() {
        String[] strArr;
        try {
            byte[] bArr = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
            DatagramSocket datagramSocket = new DatagramSocket(this.p, InetAddress.getByName(i));
            this.n = true;
            while (this.n) {
                datagramSocket.receive(datagramPacket);
                String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                datagramPacket.setLength(bArr.length);
                a(str.replace("<b>", "").replace("</b>", ""));
            }
            Log.i(a, "EXITED listener loop!");
        } catch (SocketException e) {
            e.printStackTrace();
            strArr = new String[]{e.getMessage()};
            a(-1, strArr);
        } catch (IOException e2) {
            e2.printStackTrace();
            strArr = new String[]{e2.getMessage()};
            a(-1, strArr);
        }
    }

    public void stop() {
        this.n = false;
    }
}
