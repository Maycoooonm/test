package org.xbmc.httpapi.client;

import java.util.ArrayList;
import java.util.Iterator;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IInfoClient;
import org.xbmc.api.info.GuiSettings;
import org.xbmc.api.object.FileLocation;
import org.xbmc.api.object.Host;
import org.xbmc.api.type.DirectoryMask;
import org.xbmc.api.type.MediaType;
import org.xbmc.httpapi.Connection;

public class InfoClient implements IInfoClient {
    private final Connection a;

    public InfoClient(Connection connection) {
        this.a = connection;
    }

    private ArrayList<String> a(INotifiableManager iNotifiableManager, String str, int i) {
        String name = MediaType.getName(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(str);
        return this.a.getArray(iNotifiableManager, "GetMediaLocation", stringBuilder.toString());
    }

    private ArrayList<String> a(INotifiableManager iNotifiableManager, String str, DirectoryMask directoryMask, int i, int i2) {
        Connection connection = this.a;
        String str2 = "GetDirectory";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(directoryMask != null ? directoryMask.toString() : " ");
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(i > 0 ? Integer.valueOf(i) : " ");
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(i2 > 0 ? Integer.valueOf(i2) : " ");
        return connection.getArray(iNotifiableManager, str2, stringBuilder.toString());
    }

    public String getCurrentlyPlayingThumbURI(INotifiableManager iNotifiableManager) {
        ArrayList array = this.a.getArray(iNotifiableManager, "GetCurrentlyPlaying", " ; ; ;true");
        int i = 0;
        Boolean valueOf = Boolean.valueOf(false);
        Iterator it = array.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith("SlideFilename")) {
                valueOf = Boolean.valueOf(true);
            } else if (str.startsWith("Thumb")) {
                if (valueOf.booleanValue()) {
                    i++;
                    if (i == 2) {
                    }
                }
                return this.a.getUrl("FileDownload", str.substring(6));
            } else {
                continue;
            }
        }
        return null;
    }

    public ArrayList<FileLocation> getDirectory(INotifiableManager iNotifiableManager, String str, int i) {
        return getDirectory(iNotifiableManager, str, null, 0, 0, i);
    }

    public ArrayList<FileLocation> getDirectory(INotifiableManager iNotifiableManager, String str, DirectoryMask directoryMask, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        ArrayList a = i3 == -1 ? a(iNotifiableManager, str, directoryMask, i, i2) : a(iNotifiableManager, str, i3);
        ArrayList<FileLocation> arrayList2 = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList2.add(new FileLocation((String) it.next()));
        }
        return arrayList2;
    }

    public boolean getGuiSettingBool(INotifiableManager iNotifiableManager, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GuiSettings.getType(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(GuiSettings.getName(i));
        return this.a.getBoolean(iNotifiableManager, "GetGuiSetting", stringBuilder.toString());
    }

    public int getGuiSettingInt(INotifiableManager iNotifiableManager, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GuiSettings.getType(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(GuiSettings.getName(i));
        return this.a.getInt(iNotifiableManager, "GetGuiSetting", stringBuilder.toString());
    }

    public String getMusicInfo(INotifiableManager iNotifiableManager, int i) {
        return this.a.getString(iNotifiableManager, "GetMusicLabel", String.valueOf(i));
    }

    public ArrayList<FileLocation> getShares(INotifiableManager iNotifiableManager, int i) {
        ArrayList array = this.a.getArray(iNotifiableManager, "GetShares", MediaType.getName(i));
        ArrayList<FileLocation> arrayList = new ArrayList();
        Iterator it = array.iterator();
        while (it.hasNext()) {
            arrayList.add(new FileLocation((String) it.next()));
        }
        return arrayList;
    }

    public String getSystemInfo(INotifiableManager iNotifiableManager, int i) {
        return this.a.getString(iNotifiableManager, "GetSystemInfo", String.valueOf(i));
    }

    public String getVideoInfo(INotifiableManager iNotifiableManager, int i) {
        return this.a.getString(iNotifiableManager, "GetVideoLabel", String.valueOf(i));
    }

    public boolean setGuiSettingBool(INotifiableManager iNotifiableManager, int i, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GuiSettings.getType(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(GuiSettings.getName(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(z);
        return this.a.getBoolean(iNotifiableManager, "SetGuiSetting", stringBuilder.toString());
    }

    public boolean setGuiSettingInt(INotifiableManager iNotifiableManager, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GuiSettings.getType(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(GuiSettings.getName(i));
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append(i2);
        return this.a.getBoolean(iNotifiableManager, "SetGuiSetting", stringBuilder.toString());
    }

    public void setHost(Host host) {
        this.a.setHost(host);
    }
}
