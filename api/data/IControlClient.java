package org.xbmc.api.data;

import java.io.Serializable;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.type.SeekType;

public interface IControlClient extends IClient {

    public interface ICurrentlyPlaying extends Serializable {
        String getAlbum();

        String getArtist();

        int getDuration();

        String getFilename();

        int getHeight();

        int getMediaType();

        float getPercentage();

        int getPlayStatus();

        int getPlaylistPosition();

        int getTime();

        String getTitle();

        int getWidth();

        boolean isPlaying();
    }

    boolean addToPlaylist(INotifiableManager iNotifiableManager, String str, int i);

    boolean broadcast(INotifiableManager iNotifiableManager, String str);

    boolean clearPlaylist(INotifiableManager iNotifiableManager, int i);

    int getBroadcast(INotifiableManager iNotifiableManager);

    ICurrentlyPlaying getCurrentlyPlaying(INotifiableManager iNotifiableManager);

    int getPercentage(INotifiableManager iNotifiableManager);

    int getPlayState(INotifiableManager iNotifiableManager);

    int getPlaylistId(INotifiableManager iNotifiableManager);

    int getVolume(INotifiableManager iNotifiableManager);

    boolean mute(INotifiableManager iNotifiableManager);

    boolean navDown(INotifiableManager iNotifiableManager);

    boolean navLeft(INotifiableManager iNotifiableManager);

    boolean navRight(INotifiableManager iNotifiableManager);

    boolean navSelect(INotifiableManager iNotifiableManager);

    boolean navUp(INotifiableManager iNotifiableManager);

    boolean pause(INotifiableManager iNotifiableManager);

    boolean playFile(INotifiableManager iNotifiableManager, String str, int i);

    boolean playNext(INotifiableManager iNotifiableManager);

    boolean playPrevious(INotifiableManager iNotifiableManager);

    boolean playUrl(INotifiableManager iNotifiableManager, String str);

    boolean seek(INotifiableManager iNotifiableManager, SeekType seekType, int i);

    boolean sendText(INotifiableManager iNotifiableManager, String str);

    boolean setBroadcast(INotifiableManager iNotifiableManager, int i, int i2);

    boolean setCurrentPlaylist(INotifiableManager iNotifiableManager, int i);

    boolean setGuiSetting(INotifiableManager iNotifiableManager, int i, String str);

    boolean setPlaylistId(INotifiableManager iNotifiableManager, int i);

    boolean setPlaylistPos(INotifiableManager iNotifiableManager, int i, int i2);

    boolean setVolume(INotifiableManager iNotifiableManager, int i);

    boolean showPicture(INotifiableManager iNotifiableManager, String str);

    boolean stop(INotifiableManager iNotifiableManager);

    boolean updateLibrary(INotifiableManager iNotifiableManager, String str);
}
