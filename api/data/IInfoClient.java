package org.xbmc.api.data;

import java.util.ArrayList;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.FileLocation;
import org.xbmc.api.type.DirectoryMask;

public interface IInfoClient extends IClient {
    String getCurrentlyPlayingThumbURI(INotifiableManager iNotifiableManager);

    ArrayList<FileLocation> getDirectory(INotifiableManager iNotifiableManager, String str, int i);

    ArrayList<FileLocation> getDirectory(INotifiableManager iNotifiableManager, String str, DirectoryMask directoryMask, int i, int i2, int i3);

    boolean getGuiSettingBool(INotifiableManager iNotifiableManager, int i);

    int getGuiSettingInt(INotifiableManager iNotifiableManager, int i);

    String getMusicInfo(INotifiableManager iNotifiableManager, int i);

    ArrayList<FileLocation> getShares(INotifiableManager iNotifiableManager, int i);

    String getSystemInfo(INotifiableManager iNotifiableManager, int i);

    String getVideoInfo(INotifiableManager iNotifiableManager, int i);

    boolean setGuiSettingBool(INotifiableManager iNotifiableManager, int i, boolean z);

    boolean setGuiSettingInt(INotifiableManager iNotifiableManager, int i, int i2);
}
