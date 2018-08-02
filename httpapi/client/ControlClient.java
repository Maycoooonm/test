package org.xbmc.httpapi.client;

import java.util.HashMap;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IControlClient;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;
import org.xbmc.api.info.GuiSettings;
import org.xbmc.api.info.PlayStatus;
import org.xbmc.api.object.Episode;
import org.xbmc.api.object.Host;
import org.xbmc.api.type.SeekType;
import org.xbmc.httpapi.Connection;

public class ControlClient implements IControlClient {
    private final Connection a;

    public ControlClient(Connection connection) {
        this.a = connection;
    }

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, String str, int i) {
        return this.a.getBoolean(iNotifiableManager, "AddToPlayList", str);
    }

    public boolean broadcast(INotifiableManager iNotifiableManager, String str) {
        return this.a.getBoolean(iNotifiableManager, "Broadcast", str);
    }

    public boolean clearPlaylist(INotifiableManager iNotifiableManager, int i) {
        return this.a.getBoolean(iNotifiableManager, "ClearPlayList", String.valueOf(i));
    }

    public int getBroadcast(org.xbmc.api.business.INotifiableManager r4) {
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
        r3 = this;
        r0 = r3.a;
        r1 = "GetBroadcast";
        r4 = r0.getString(r4, r1);
        r0 = ";";
        r4 = r4.split(r0);
        r0 = 1;
        r1 = 0;
        r2 = r4[r0];	 Catch:{ NumberFormatException -> 0x0023 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0023 }
        if (r2 <= r0) goto L_0x0023;	 Catch:{ NumberFormatException -> 0x0023 }
    L_0x0018:
        r4 = r4[r1];	 Catch:{ NumberFormatException -> 0x0023 }
        r0 = "0";	 Catch:{ NumberFormatException -> 0x0023 }
        r4 = r4.equals(r0);	 Catch:{ NumberFormatException -> 0x0023 }
        if (r4 != 0) goto L_0x0023;
    L_0x0022:
        r1 = r2;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.ControlClient.getBroadcast(org.xbmc.api.business.INotifiableManager):int");
    }

    public ICurrentlyPlaying getCurrentlyPlaying(INotifiableManager iNotifiableManager) {
        HashMap pairs = this.a.getPairs(iNotifiableManager, "GetCurrentlyPlaying", " ; ; ;true");
        ICurrentlyPlaying anonymousClass1 = new ICurrentlyPlaying(this) {
            private static final long b = -1554068775915058884L;
            final /* synthetic */ ControlClient a;

            {
                this.a = r1;
            }

            public String getAlbum() {
                return "";
            }

            public String getArtist() {
                return "";
            }

            public int getDuration() {
                return 0;
            }

            public String getFilename() {
                return "";
            }

            public int getHeight() {
                return 0;
            }

            public int getMediaType() {
                return 0;
            }

            public float getPercentage() {
                return 0.0f;
            }

            public int getPlayStatus() {
                return 2;
            }

            public int getPlaylistPosition() {
                return -1;
            }

            public int getTime() {
                return 0;
            }

            public String getTitle() {
                return "";
            }

            public int getWidth() {
                return 0;
            }

            public boolean isPlaying() {
                return false;
            }
        };
        if (pairs == null) {
            return anonymousClass1;
        }
        if ((pairs.get("Filename") != null && ((String) pairs.get("Filename")).contains("Nothing Playing")) || !pairs.containsKey("Type")) {
            return anonymousClass1;
        }
        Object obj = ((String) pairs.get("Type")).contains("Audio") ? 1 : ((String) pairs.get("Type")).contains("Video") ? (pairs.get("Show Title") == null || pairs.get(Episode.TAG) == null) ? 2 : 24 : 3;
        if (obj == 24) {
            return TvShowClient.a(pairs);
        }
        switch (obj) {
            case 1:
                return MusicClient.a(pairs);
            case 2:
                return VideoClient.a(pairs);
            case 3:
                return PictureClient.a(pairs);
            default:
                return anonymousClass1;
        }
    }

    public int getPercentage(INotifiableManager iNotifiableManager) {
        return this.a.getInt(iNotifiableManager, "GetPercentage");
    }

    public int getPlayState(INotifiableManager iNotifiableManager) {
        return PlayStatus.parse(this.a.getString(iNotifiableManager, "GetCurrentlyPlaying"));
    }

    public int getPlaylistId(INotifiableManager iNotifiableManager) {
        return this.a.getInt(iNotifiableManager, "GetCurrentPlaylist");
    }

    public int getVolume(INotifiableManager iNotifiableManager) {
        return this.a.getInt(iNotifiableManager, "GetVolume");
    }

    public boolean mute(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Mute");
    }

    public boolean navDown(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Action", String.valueOf(4));
    }

    public boolean navLeft(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Action", String.valueOf(1));
    }

    public boolean navRight(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Action", String.valueOf(2));
    }

    public boolean navSelect(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Action", String.valueOf(7));
    }

    public boolean navUp(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Action", String.valueOf(3));
    }

    public boolean pause(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Pause");
    }

    public boolean playFile(INotifiableManager iNotifiableManager, String str, int i) {
        return this.a.getBoolean(iNotifiableManager, "PlayFile", str);
    }

    public boolean playNext(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "PlayNext");
    }

    public boolean playPrevious(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "PlayPrev");
    }

    public boolean playUrl(INotifiableManager iNotifiableManager, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayMedia(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return this.a.getBoolean(iNotifiableManager, "ExecBuiltin", stringBuilder.toString());
    }

    public boolean seek(INotifiableManager iNotifiableManager, SeekType seekType, int i) {
        Connection connection;
        String str;
        if (seekType.compareTo(SeekType.absolute) == 0) {
            connection = this.a;
            str = "SeekPercentage";
        } else {
            connection = this.a;
            str = "SeekPercentageRelative";
        }
        return connection.getBoolean(iNotifiableManager, str, String.valueOf(i));
    }

    public boolean sendText(INotifiableManager iNotifiableManager, String str) {
        for (char c : str.toCharArray()) {
            if (!this.a.getBoolean(iNotifiableManager, "SendKey", Integer.toString(c + 61696))) {
                return false;
            }
        }
        return true;
    }

    public boolean setBroadcast(INotifiableManager iNotifiableManager, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i2);
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(i);
        return this.a.getBoolean(iNotifiableManager, "SetBroadcast", stringBuilder.toString());
    }

    public boolean setCurrentPlaylist(INotifiableManager iNotifiableManager, int i) {
        return this.a.getBoolean(iNotifiableManager, "SetCurrentPlaylist", String.valueOf(i));
    }

    public boolean setGuiSetting(INotifiableManager iNotifiableManager, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GuiSettings.getType(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(GuiSettings.getName(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(str);
        return this.a.getBoolean(iNotifiableManager, "SetGUISetting", stringBuilder.toString());
    }

    public void setHost(Host host) {
        this.a.setHost(host);
    }

    public boolean setPlaylistId(INotifiableManager iNotifiableManager, int i) {
        return this.a.getBoolean(iNotifiableManager, "SetCurrentPlaylist", String.valueOf(i));
    }

    public boolean setPlaylistPos(INotifiableManager iNotifiableManager, int i, int i2) {
        return this.a.getBoolean(iNotifiableManager, "SetPlaylistSong", String.valueOf(i2));
    }

    public boolean setResponseFormat(org.xbmc.api.business.INotifiableManager r4) {
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
        r3 = this;
        r0 = new java.lang.StringBuilder;	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.<init>();	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "WebHeader;true;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "WebFooter;true;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "Header; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "Footer; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "OpenTag;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "<li>";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = ";";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "CloseTag;\n;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "CloseFinalTag;false";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = r3.a;	 Catch:{ WrongDataFormatException -> 0x006d }
        r2 = "SetResponseFormat";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0 = r0.toString();	 Catch:{ WrongDataFormatException -> 0x006d }
        r1.assertBoolean(r4, r2, r0);	 Catch:{ WrongDataFormatException -> 0x006d }
        r0 = new java.lang.StringBuilder;	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.<init>();	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "OpenRecordSet; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "CloseRecordSet; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "OpenRecord; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "CloseRecord; ;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "OpenField;<field>;";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = "CloseField;</field>";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0.append(r1);	 Catch:{ WrongDataFormatException -> 0x006d }
        r1 = r3.a;	 Catch:{ WrongDataFormatException -> 0x006d }
        r2 = "SetResponseFormat";	 Catch:{ WrongDataFormatException -> 0x006d }
        r0 = r0.toString();	 Catch:{ WrongDataFormatException -> 0x006d }
        r1.assertBoolean(r4, r2, r0);	 Catch:{ WrongDataFormatException -> 0x006d }
        r4 = 1;
        return r4;
    L_0x006d:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.ControlClient.setResponseFormat(org.xbmc.api.business.INotifiableManager):boolean");
    }

    public boolean setVolume(INotifiableManager iNotifiableManager, int i) {
        return this.a.getBoolean(iNotifiableManager, "SetVolume", String.valueOf(i));
    }

    public boolean showPicture(INotifiableManager iNotifiableManager, String str) {
        this.a.getBoolean(iNotifiableManager, "ClearSlideshow");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(0, str.replaceAll("\\\\", "/").lastIndexOf("/")));
        stringBuilder.append(";false");
        this.a.getBoolean(iNotifiableManager, "PlaySlideshow", stringBuilder.toString());
        this.a.getBoolean(iNotifiableManager, "SlideshowSelect", str);
        return playNext(iNotifiableManager);
    }

    public boolean stop(INotifiableManager iNotifiableManager) {
        return this.a.getBoolean(iNotifiableManager, "Stop");
    }

    public boolean updateLibrary(INotifiableManager iNotifiableManager, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UpdateLibrary(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return this.a.getBoolean(iNotifiableManager, "ExecBuiltin", stringBuilder.toString());
    }
}
